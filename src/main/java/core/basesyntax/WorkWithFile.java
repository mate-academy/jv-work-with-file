package core.basesyntax;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_STRING = ",";
    private static final String STRING_BUY = "buy";
    private static final String STRING_SUPPLY = "supply";
    private static final String STRING_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = createReport(readingFomCSV(fromFileName));
        StringBuilderWritingToCSV(stringBuilder.toString(), toFileName);
    }

    private void StringBuilderWritingToCSV (String stringData, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: ", e);
        }
    }

    private String readingFomCSV (String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            stringBuilder.append(value);
            while (value != null) {
                value = reader.readLine();
                stringBuilder.append(",").append(value);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't open file: ", e);
        }
    }

    private StringBuilder createReport (String dataString) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = dataString.split(SPLIT_STRING);
        for (int i=0; i<split.length; i = i + 2) {
            if (split[i].equals(STRING_BUY)) {
                buy = buy + Integer.parseInt(split[i+1]);
            }
            if (split[i].equals("supply")) {
                supply = supply + Integer.parseInt(split[i+1]);
            }
        }
        stringBuilder.append(STRING_SUPPLY).append(SPLIT_STRING).append(supply).append(System.lineSeparator());
        stringBuilder.append(STRING_BUY).append(SPLIT_STRING).append(buy).append(System.lineSeparator());
        stringBuilder.append(STRING_RESULT).append(SPLIT_STRING).append(supply-buy).append(System.lineSeparator());
        return  stringBuilder;
    }
}
