package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SPLIT_STRING = ",";
    private static final String STRING_BUY = "buy";
    private static final String STRING_SUPPLY = "supply";
    private static final String STRING_RESULT = "result";
    private static final Byte OPERATION_INDEX = 0;
    private static final Byte QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFomCsv(fromFileName);
        String report = createReport(data);
        writeToCsv(report, toFileName);
    }

    private List<String> readFomCsv(String fromFileName) {
        List<String> data = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                data.add(value);
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't open file: " + fromFileName, e);
        }
    }

    private String createReport(List<String> data) {
        int buy = 0;
        int supply = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String line:data) {
            String[] split = line.split(SPLIT_STRING);
            if (split[OPERATION_INDEX].equals(STRING_BUY)) {
                buy = buy + Integer.parseInt(split[QUANTITY_INDEX]);
            }
            if (split[OPERATION_INDEX].equals(STRING_SUPPLY)) {
                supply = supply + Integer.parseInt(split[QUANTITY_INDEX]);
            }
        }
        stringBuilder.append(STRING_SUPPLY).append(SPLIT_STRING).append(supply)
                .append(System.lineSeparator());
        stringBuilder.append(STRING_BUY).append(SPLIT_STRING).append(buy)
                .append(System.lineSeparator());
        stringBuilder.append(STRING_RESULT).append(SPLIT_STRING).append(supply - buy)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToCsv(String stringData, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}
