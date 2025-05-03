package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String RESULT_TYPE = "result";
    private static final String SUPPLY_TYPE = "supply";
    private static final String BUY_TYPE = "buy";
    private static final String DELIMITER = " ";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readFromFileString = readFromFile(fromFileName);
        String makeReportString = makeReport(readFromFileString);
        writeToFile(makeReportString, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(DELIMITER);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String makeReport(String input) {
        int supply = 0;
        int buy = 0;
        String[] inputArray = input.split(DELIMITER);
        for (String element : inputArray) {
            String[] elementArray = element.split(COMMA_SEPARATOR);
            if (elementArray[OPERATION_INDEX].equals(SUPPLY_TYPE)) {
                supply += Integer.parseInt(elementArray[AMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(elementArray[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilderResult = new StringBuilder()
                .append(SUPPLY_TYPE).append(COMMA_SEPARATOR).append(supply)
                .append(System.lineSeparator())
                .append(BUY_TYPE).append(COMMA_SEPARATOR).append(buy)
                .append(System.lineSeparator())
                .append(RESULT_TYPE).append(COMMA_SEPARATOR).append(result);
        return stringBuilderResult.toString();
    }

    private void writeToFile(String inputDate, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(inputDate);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
