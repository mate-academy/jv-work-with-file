package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readFile(fromFileName));
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            return reader.lines().toArray(String[]::new);

        } catch (IOException e) {
            throw new RuntimeException("Can`t read file");
        }
    }

    private String createReport(String[] lines) {
        int supplyAmount = 0;
        int buyAmount = 0;
        int result;
        StringBuilder stringBuilder = new StringBuilder();

        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            String operationType = parts[0];
            int operationAmount = Integer.parseInt(parts[1]);

            if (operationType.equals(SUPPLY)) {
                supplyAmount += operationAmount;
            } else if (operationType.equals(BUY)) {
                buyAmount += operationAmount;
            }

        }

        result = supplyAmount - buyAmount;
        String report = stringBuilder.append(SUPPLY).append(SEPARATOR)
                .append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buyAmount)
                .append(System.lineSeparator()).append(RESULT).append(SEPARATOR)
                .append(result).append(System.lineSeparator())
                .toString();

        return report;
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }
}
