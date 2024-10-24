package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX_OF_ACTION = 0;
    private static final int INDEX_OF_QUANTITY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = parseDataFromFile(fromFileName);
        String report = createReport(dataFromFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file", exception);
        }
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String[] arrayString = dataFromFile.split(System.lineSeparator());
        for (int i = 0; i < arrayString.length; i++) {
            String[] tempArrayDetail = arrayString[i].split(COMMA);
            if (tempArrayDetail[INDEX_OF_ACTION].equals(SUPPLY)) {
                supply += Integer.parseInt(tempArrayDetail[INDEX_OF_QUANTITY]);
            } else if (tempArrayDetail[INDEX_OF_ACTION].equals(BUY)) {
                buy += Integer.parseInt(tempArrayDetail[INDEX_OF_QUANTITY]);
            }
        }
        result = supply - buy;
        return SUPPLY + COMMA + supply + System.lineSeparator()
                + BUY + COMMA + buy + System.lineSeparator()
                + RESULT + COMMA + result;
    }

    private String parseDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException exception) {
            throw new RuntimeException("File doesn't exist");
        }
    }
}
