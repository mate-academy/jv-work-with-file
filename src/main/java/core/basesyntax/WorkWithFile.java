package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATE_COMMA = ",";
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_INDEX = 0;
    private static final String RESULT_VALUE = "result";
    private static final String BUY_VALUE = "buy";
    private static final String SUPPLY_VALUE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String value = readFile(fromFileName);
        String finalReport = createReport(value);
        writeFile(toFileName, finalReport);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    private void writeFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String createReport(String contents) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lines = contents.trim().split(System.lineSeparator());
        for (String line : lines) {
            String[] splitter = line.split(SEPARATE_COMMA);
            String operation = splitter[OPERATION_INDEX].trim();
            int amount = Integer.parseInt(splitter[AMOUNT_INDEX].trim());
            switch (operation) {
                case SUPPLY_VALUE:
                    totalSupply += amount;
                    break;
                case BUY_VALUE:
                    totalBuy += amount;
                    break;
                default:
            }
        }
        int result = totalSupply - totalBuy;
        return SUPPLY_VALUE + SEPARATE_COMMA + totalSupply + System.lineSeparator()
                + BUY_VALUE + SEPARATE_COMMA + totalBuy + System.lineSeparator()
                + RESULT_VALUE + SEPARATE_COMMA + result;
    }
}
