package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String REPORT_FORMAT = "supply,%d" + System.lineSeparator()
            + "buy,%d" + System.lineSeparator()
            + "result,%d";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        String report = getReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String nextLine = reader.readLine();
            while (nextLine != null) {
                result.append(nextLine).append(System.lineSeparator());
                nextLine = reader.readLine();
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }

    private String getReport(String fileContent) {
        String[] lines = fileContent.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] lineParts = line.split(CSV_SEPARATOR);
            String operationType = lineParts[OPERATION_TYPE_INDEX];
            int value = Integer.parseInt(lineParts[VALUE_INDEX]);
            switch (OperationType.valueOf(operationType.toUpperCase())) {
                case SUPPLY:
                    supply += value;
                    break;
                case BUY:
                    buy += value;
                    break;
                default:break;
            }
        }
        return String.format(REPORT_FORMAT, supply, buy, supply - buy);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
