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
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String[] result = createResult(data);
        writeFile(toFileName, result);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fromFileName, e);
        }
        return data.toString().split(System.lineSeparator());
    }

    private String[] createResult(String[] data) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : data) {
            String[] parts = line.split(COMMA_SEPARATOR);
            String operationType = parts[OPERATION_TYPE_INDEX].trim();
            int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

            if (SUPPLY.equals(operationType)) {
                totalSupply += amount;
            } else if (BUY.equals(operationType)) {
                totalBuy += amount;
            }
        }
        int result = totalSupply - totalBuy;
        return new String[] {
            SUPPLY + COMMA_SEPARATOR + totalSupply,
            BUY + COMMA_SEPARATOR + totalBuy,
            RESULT + COMMA_SEPARATOR + result
        };
    }

    private void writeFile(String toFileName, String[] result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String line : result) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to a file: " + toFileName, e);
        }
    }
}
