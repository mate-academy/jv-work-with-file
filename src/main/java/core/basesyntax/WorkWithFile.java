package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String CSV_SEPARATOR = ",";
    public static final int CSV_OP_TYPE_INDEX = 0;
    public static final int CSV_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        String report = getReport(fileContent);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Can't write to file: " + toFileName);
        }
    }

    private String getReport(String fileContent) {
        String[] lines = fileContent.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] lineParts = line.split(CSV_SEPARATOR);
            String opType = lineParts[CSV_OP_TYPE_INDEX];
            int value = Integer.parseInt(lineParts[CSV_VALUE_INDEX]);
            switch (OperationType.valueOf(opType.toUpperCase())) {
                case SUPPLY:
                    supply += value;
                    break;
                case BUY:
                    buy += value;
                    break;
                default:break;
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder result = new StringBuilder();
        try (
                FileReader fileReader = new FileReader(fromFileName);
                BufferedReader reader = new BufferedReader(fileReader)
        ) {
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
}
