package core.basesyntax;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_LABEL = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> reports = readAndAggregate(new File(fromFileName));
        String report = buildReport(reports);
        writeReport(new File(toFileName), report);
    }

    private Map<String, Integer> readAndAggregate(File file) {
        Map<String, Integer> reports = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                accumulateLine(line, reports);
            }
            return reports;
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + file.getPath(), e);
        }
    }

    private void accumulateLine(String line, Map<String, Integer> reports) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != 2) {
            throw new RuntimeException("Invalid line format: " + line);
        }

        String operation = parts[0].trim();
        String amountStr = parts[1].trim();

        if (!operation.equals(SUPPLY) && !operation.equals(BUY)) {
            throw new RuntimeException("Unexpected operation: " + operation);
        }

        final int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number: " + amountStr, e);
        }

        reports.merge(operation, amount, Integer::sum);
    }

    private String buildReport(Map<String, Integer> reports) {
        int supply = reports.getOrDefault(SUPPLY, 0);
        int buy = reports.getOrDefault(BUY, 0);
        int resultValue = supply - buy;

        String nl = System.lineSeparator();
        return SUPPLY + DELIMITER + supply + nl
                + BUY + DELIMITER + buy + nl
                + RESULT_LABEL + DELIMITER + resultValue;
    }

    private void writeReport(File toFile, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + toFile.getPath(), e);
        }
    }
}
