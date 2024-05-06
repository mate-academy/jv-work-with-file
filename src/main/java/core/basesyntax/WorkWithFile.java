package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String VALUE_SUPPLY = "supply";
    static final String VALUE_BUY = "buy";
    static final String VALUE_RESULT = "result";
    static final int ZERO_INDEX = 0;
    static final int FIRST_INDEX = 1;
    static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder dataFromFile = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                dataFromFile.append(line).append(System.lineSeparator());
            }
            return dataFromFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            String operationType = parts[ZERO_INDEX].trim();
            int amount = Integer.parseInt(parts[FIRST_INDEX].trim());

            if (operationType.equals(VALUE_SUPPLY)) {
                supplyTotal += amount;
            } else if (operationType.equals(VALUE_BUY)) {
                buyTotal += amount;
            }
        }

        int result = supplyTotal - buyTotal;

        return VALUE_SUPPLY + SEPARATOR + supplyTotal + System.lineSeparator()
                + VALUE_BUY + SEPARATOR + buyTotal + System.lineSeparator()
                + VALUE_RESULT + SEPARATOR + result;
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
