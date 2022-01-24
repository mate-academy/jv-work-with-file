package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataInfo = readFile(fromFileName);
        String report = processingData(dataInfo);
        writeReport(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(SEPARATOR);
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private String processingData(String dataInfo) {
        String[] valueArr = dataInfo.split(SEPARATOR);
        int supply = 0;
        int buy = 0;
        int counter = 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < valueArr.length; i += 2) {
            if (valueArr[i].equals(SUPPLY)) {
                supply += Integer.parseInt(valueArr[counter]);
            } else {
                buy += Integer.parseInt(valueArr[counter]);
            }
            counter += 2;
        }
        builder.append(SUPPLY + SEPARATOR).append(supply).append(System.lineSeparator())
                .append(BUY + SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT + SEPARATOR).append(supply - buy).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeReport(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't writer file" + toFileName, e);
        }
    }
}
