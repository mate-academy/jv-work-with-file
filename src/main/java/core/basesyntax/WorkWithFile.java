package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_VALUE = "supply";
    private static final String BUY_VALUE = "buy";
    private static final String RESULT_VALUE = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    private static final String SEPARATE_COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String contents = readFile(fromFileName);
        String report = generateReport(contents);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

    private String generateReport(String contents) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lines = contents.trim().split(System.lineSeparator());
        for (String line : lines) {
            String[] splitter = line.split(SEPARATE_COMA);
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
        return SUPPLY_VALUE + SEPARATE_COMA + totalSupply + System.lineSeparator()
                + BUY_VALUE + SEPARATE_COMA + totalBuy + System.lineSeparator()
                + RESULT_VALUE + SEPARATE_COMA + result;
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file" + fileName, e);
        }
    }
}
