package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readFromFile(fromFileName);
        String report = generateReport(statistics);
        writeResultToFile(report, toFileName);
    }

    private int[] readFromFile(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(COMMA);
                String operation = data[OPERATION_INDEX];
                int amount = Integer.parseInt(data[AMOUNT_INDEX]);

                if (operation.equals(SUPPLY)) {
                    totalSupply += amount;
                } else if (operation.equals(BUY)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file" + fromFileName, e);
        }
        return new int[]{totalSupply, totalBuy};
    }

    private String generateReport(int[] statistics) {
        int totalSupply = statistics[0];
        int totalBuy = statistics[1];
        int result = totalSupply - totalBuy;
        String[] operations = {SUPPLY, BUY, RESULT};
        int[] amounts = {totalSupply, totalBuy, result};
        StringBuilder reportBuilder = new StringBuilder();
        for (int i = 0; i < operations.length; i++) {
            reportBuilder.append(operations[i])
                    .append(COMMA)
                    .append(amounts[i])
                    .append(System.lineSeparator());
        }
        return reportBuilder.toString();
    }

    private void writeResultToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file" + toFileName, e);
        }
    }
}
