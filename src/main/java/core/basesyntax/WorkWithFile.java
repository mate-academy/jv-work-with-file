package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readAndAggregate(fromFileName);
        String report = buildReportString(totals[0], totals[1]);
        writeReport(toFileName, report);
    }

    private int[] readAndAggregate(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(DELIMITER);

                if (parts.length != 2) {
                    throw new RuntimeException(
                            "Invalid line format in file: "
                                    + fromFileName + " -> \""
                                    + line + "\"");
                }

                String operation = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "Invalid number format in file: "
                                    + fromFileName + " -> \""
                                    + line + "\"", e);
                }

                switch (operation) {
                    case SUPPLY:
                        supply += amount;
                        break;
                    case BUY:
                        buy += amount;
                        break;
                    default:
                        throw new RuntimeException(
                                "Unknown operation in file: "
                                        + fromFileName + " -> \""
                                        + line + "\"");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }

        return new int[]{supply, buy};
    }

    private String buildReportString(int supply, int buy) {
        int result = supply - buy;
        return new StringBuilder()
                .append(SUPPLY).append(DELIMITER).append(supply).append(LINE_SEPARATOR)
                .append(BUY).append(DELIMITER).append(buy).append(LINE_SEPARATOR)
                .append(RESULT).append(DELIMITER).append(result).append(LINE_SEPARATOR)
                .toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}
