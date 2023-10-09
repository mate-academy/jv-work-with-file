package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_LITERAL = "supply";
    private static final String BUY_LITERAL = "buy";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supplyTotal = 0;
            int buyTotal = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(SEPARATOR);
                String operationType = fields[0];
                int amount = Integer.parseInt(fields[1]);

                if (SUPPLY_LITERAL.equals(operationType)) {
                    supplyTotal += amount;
                } else if (BUY_LITERAL.equals(operationType)) {
                    buyTotal += amount;
                }

            }
            int result = supplyTotal - buyTotal;
            stringBuilder.append(SUPPLY_LITERAL).append(SEPARATOR).append(supplyTotal)
                    .append(System.lineSeparator()).append(BUY_LITERAL).append(SEPARATOR)
                    .append(buyTotal).append(System.lineSeparator()).append("result")
                    .append(SEPARATOR).append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
