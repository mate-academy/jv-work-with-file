package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String OP_SUPPLY = "supply";
    private static final String OP_BUY = "buy";
    private static final String OP_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLines(fromFileName);
        int[] aggregated = aggregate(lines);
        String report = buildReport(aggregated[0], aggregated[1]);
        writeReport(toFileName, report);
    }

    private List<String> readLines(String fromFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return lines;
    }

    private int[] aggregate(List<String> lines) {
        int supplySum = 0;
        int buySum = 0;

        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            if (parts.length != 2) {
                throw new RuntimeException("Invalid line format: " + line);
            }

            String operation = parts[0].trim();
            int amount;
            try {
                amount = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in line: " + line, e);
            }

            switch (operation) {
                case OP_SUPPLY:
                    supplySum += amount;
                    break;
                case OP_BUY:
                    buySum += amount;
                    break;
                default:
                    throw new RuntimeException("Unknown operation: " + operation);
            }
        }
        return new int[] { supplySum, buySum };
    }

    private String buildReport(int supplySum, int buySum) {
        int result = supplySum - buySum;
        StringBuilder sb = new StringBuilder();
        sb.append(OP_SUPPLY).append(DELIMITER).append(supplySum).append(System.lineSeparator());
        sb.append(OP_BUY).append(DELIMITER).append(buySum).append(System.lineSeparator());
        sb.append(OP_RESULT).append(DELIMITER).append(result);
        return sb.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
