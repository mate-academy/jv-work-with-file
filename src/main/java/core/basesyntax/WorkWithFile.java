package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String WHITESPACE = " ";
    public static final String COMMA = ",";
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";
    public static final int INDEX_OF_ACTION = 0;
    public static final int INDEX_OF_QUANTITY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = parseDataFromFile(fromFileName);
        String reportString = createReport(dataFromFile);
        writeReportToFile(toFileName, reportString);
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        int result;

        String[] stringsArray = dataFromFile.split(WHITESPACE);
        for (String value : stringsArray) {
            String[] temporaryArray = value.split(COMMA);
            if (temporaryArray[INDEX_OF_ACTION].equals(BUY)) {
                buy += Integer.parseInt(temporaryArray[INDEX_OF_QUANTITY]);
            } else if (temporaryArray[INDEX_OF_ACTION].equals(SUPPLY)) {
                supply += Integer.parseInt(temporaryArray[INDEX_OF_QUANTITY]);
            }
        }
        result = supply - buy;
        return SUPPLY + COMMA + supply + System.lineSeparator()
                + BUY + COMMA + buy + System.lineSeparator()
                + RESULT + COMMA + result;
    }

    private String parseDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(WHITESPACE);
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException ioException) {
            throw new RuntimeException("Can't read data from the file: "
                    + fromFileName, ioException);
        }
    }

    private static void writeReportToFile(String toFileName, String reportString) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileName, true))) {
            bufferedWriter.write(reportString);
        } catch (IOException ioException) {
            throw new RuntimeException("Can't write to the file: " + toFileName, ioException);
        }
    }
}
