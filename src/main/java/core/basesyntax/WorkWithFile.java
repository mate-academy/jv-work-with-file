package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int PARTS_AMOUNT = 2;
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName)
            throws IllegalArgumentException {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) throws IllegalArgumentException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }
        return data.toString();
    }

    private String createReport(String data) throws IllegalArgumentException {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts.length == PARTS_AMOUNT) {
                String type = parts[OPERATION_TYPE].trim();
                int value = Integer.parseInt(parts[AMOUNT].trim());
                if ("supply".equals(type)) {
                    supplyTotal += value;
                } else if ("buy".equals(type)) {
                    buyTotal += value;
                }
            }
        }
        int result = supplyTotal - buyTotal;
        if (supplyTotal < 0 || buyTotal < 0 || result < 0) {
            throw new IllegalArgumentException("The file contains wrong data");
        }
        return SUPPLY + COMMA + supplyTotal + System.lineSeparator()
                + BUY + COMMA + buyTotal + System.lineSeparator()
                + RESULT + COMMA + result;
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file");
        }
    }
}
