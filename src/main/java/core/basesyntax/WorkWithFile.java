package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_ID = 0;
    private static final int AMOUNT_ID = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String SEPARATOR = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName).split(System.lineSeparator());
        String[][] data = new String[lines.length][2];
        String[] buffer;
        for (int i = 0; i < lines.length; i++) {
            buffer = lines[i].split(SEPARATOR);
            data[i][OPERATION_ID] = buffer[OPERATION_ID];
            data[i][AMOUNT_ID] = buffer[AMOUNT_ID];
        }
        int[] report = countAmountByOperation(data);
        String output = SUPPLY + SEPARATOR + report[1] + System.lineSeparator() + BUY + SEPARATOR + report[0]
                + System.lineSeparator() + RESULT + SEPARATOR + report[2];
        writeToFile(output, toFileName);
    }

    private void writeToFile(String text, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + file, e);
        }
    }

    private String readFromFile(String file) {
        StringBuilder text = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            line = reader.readLine();
            while (line != null) {
                text.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            return text.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
    }

    private int[] countAmountByOperation(String[][] data) {
        int buyAmount = 0;
        int supplyAmount = 0;
        for (String[] line: data) {
            if (line[OPERATION_ID].equals(BUY)) {
                buyAmount += Integer.parseInt(line[AMOUNT_ID]);
                continue;
            }
            supplyAmount += Integer.parseInt(line[AMOUNT_ID]);
        }
        int result = supplyAmount - buyAmount;
        return new int[]{buyAmount, supplyAmount, result};
    }
}
