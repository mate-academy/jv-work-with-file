package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int VALID_PARTS_AMOUNT = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = processData(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(LINE_SEPARATOR);
            }
            return data.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file: " + fromFileName, ex);
        }
    }

    private String processData(String dataFromFile) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = dataFromFile.split(LINE_SEPARATOR);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != VALID_PARTS_AMOUNT) {
                throw new RuntimeException("Incorrect number of parts in the string. Expected: "
                        + VALID_PARTS_AMOUNT + ", actual: " + parts.length);
            }
            int quantity = Integer.parseInt(parts[INDEX_ONE].trim());
            if (SUPPLY.equals(parts[INDEX_ZERO].trim())) {
                supplyTotal += quantity;
            } else if (BUY.equals(parts[INDEX_ZERO].trim())) {
                buyTotal += quantity;
            }
        }
        return createReport(supplyTotal, buyTotal);
    }

    private String createReport(int supplyTotal, int buyTotal) {
        int result = supplyTotal - buyTotal;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(supplyTotal).append(LINE_SEPARATOR);
        report.append(BUY).append(",").append(buyTotal).append(LINE_SEPARATOR);
        report.append("result").append(",").append(result);

        return report.toString();
    }

    public void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}
