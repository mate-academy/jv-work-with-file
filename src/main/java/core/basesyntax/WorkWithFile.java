package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final String TYPE_AND_DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileContent = readFileContent(fromFileName);
        String report = makeReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String[] readFileContent(String fromFileName) {
        StringBuilder fromFileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                fromFileContent.append(value)
                        .append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read fromFileName: " + fromFileName, e);
        }

        return fromFileContent.toString().split(System.lineSeparator());
    }

    private String makeReport(String[] fromFileLines) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String fromFileLine : fromFileLines) {
            String[] separatedLine = fromFileLine.split(",");
            if (separatedLine[OPERATION_TYPE_INDEX].equals(OperationTypes.SUPPLY.toString())) {
                supplyAmount += Integer.parseInt(separatedLine[AMOUNT_INDEX]);
            } else {
                buyAmount += Integer.parseInt(separatedLine[AMOUNT_INDEX]);
            }
        }

        return new StringBuilder(OperationTypes.SUPPLY.toString())
                .append(TYPE_AND_DATA_SEPARATOR)
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(OperationTypes.BUY)
                .append(TYPE_AND_DATA_SEPARATOR)
                .append(buyAmount)
                .append(System.lineSeparator())
                .append(OperationTypes.RESULT)
                .append(TYPE_AND_DATA_SEPARATOR)
                .append(supplyAmount - buyAmount)
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write toFileName: " + toFileName, e);
        }
    }
}
