package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    public String readFile(String fileName) {
        StringBuilder data = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(NEW_LINE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }

        return data.toString();
    }

    private String createReport(String dataFromFile) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = dataFromFile.split(NEW_LINE);
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            if (parts.length == 2) {
                String operation = parts[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

                if (operation.equals(SUPPLY)) {
                    supplyTotal += amount;
                } else if (operation.equals(BUY)) {
                    buyTotal += amount;
                }
            }
        }

        return SUPPLY + SEPARATOR + supplyTotal + NEW_LINE
                + BUY + SEPARATOR + buyTotal + NEW_LINE
                + "result" + SEPARATOR + (supplyTotal - buyTotal);
    }

    private void writeReportToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while writing report to file: "
                    + fileName, e);
        }
    }
}
