package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String COMA = ",";
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String input = readFromFile(fromFileName);
        String report = makeReport(input);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private String makeReport(String input) {
        int totalBuy = 0;
        int totalSupply = 0;
        String[] strings = input.split(System.lineSeparator());
        for (String row : strings) {
            String[] comaDivided = row.split(COMA);
            if (row.contains(BUY)) {
                totalBuy += Integer.parseInt(comaDivided[AMOUNT_INDEX]);
            } else if (row.contains(SUPPLY)) {
                totalSupply += Integer.parseInt(comaDivided[AMOUNT_INDEX]);
            }
        }
        int result = totalSupply - totalBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(totalSupply)
                .append(System.lineSeparator()).append(BUY).append(",")
                .append(totalBuy).append(System.lineSeparator()).append("result,")
                .append(result);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,
                true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
