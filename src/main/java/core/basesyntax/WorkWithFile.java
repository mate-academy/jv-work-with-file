package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_PART = 0;
    private static final int AMOUNT_PART = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder rawData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rawData.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return rawData.toString();
    }

    private int parseAmount(String rawData, String operationType) {
        int amount = 0;
        String[] lines = rawData.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[OPERATION_TYPE_PART].equals(operationType)) {
                amount += Integer.parseInt(parts[AMOUNT_PART]);
            }
        }
        return amount;
    }

    private String generateReport(String rawData) {
        int supplyAmount = parseAmount(rawData, SUPPLY_OPERATION);
        int buyAmount = parseAmount(rawData, BUY_OPERATION);
        int resultAmount = supplyAmount - buyAmount;
        return SUPPLY_OPERATION + "," + supplyAmount + System.lineSeparator()
                + BUY_OPERATION + "," + buyAmount + System.lineSeparator()
                + "result," + resultAmount;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
