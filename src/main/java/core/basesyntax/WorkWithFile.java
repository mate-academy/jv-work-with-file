package core.basesyntax;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        String report = generateReport(lines);
        writeFile(toFileName, report);
    }

    private List<String> readFile(String fileName) {
        List<String> operationsData = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                operationsData.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return operationsData;
    }

    private String generateReport(List<String> lines) {
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                throw new RuntimeException("Invalid CSV format in line: " + line);
            }
            String operation = parts[0];
            int amount;
            try {
                amount = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in line: " + line, e);
            }

            if (operation.equals(SUPPLY)) {
                supplyAmount += amount;
            } else if (operation.equals(BUY)) {
                buyAmount += amount;
            } else {
                throw new RuntimeException("Unknown operation type: " + operation);
            }
        }

        int result = supplyAmount - buyAmount;
        return new StringBuilder()
                .append(SUPPLY).append(",").append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(",").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(result)
                .toString();
    }

    private void writeFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file " + fileName, e);
        }
    }
}
