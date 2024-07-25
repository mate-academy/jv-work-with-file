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
        String[] output = readFile(fromFileName);
        String[] result = generateReport(output);
        writeToFile(result, toFileName);
    }

    public static String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int linesCount = 0;
            while (reader.readLine() != null) {
                linesCount++;
            }
            String[] output = new String[linesCount];
            try (BufferedReader readerAgain = new BufferedReader(new FileReader(fromFileName))) {
                String line;
                int index = 0;
                while ((line = readerAgain.readLine()) != null) {
                    output[index++] = line.trim();
                }
            }
            return output;
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
    }

    public static String[] generateReport(String[] output) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] report = new String[3];
        for (String part : output) {
            String[] parts = part.split(COMMA_SEPARATOR);
            String operationType = parts[OPERATION_TYPE_INDEX].trim();
            int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
            if (SUPPLY.equals(operationType)) {
                totalSupply += amount;
            }
            if (BUY.equals(operationType)) {
                totalBuy += amount;
            }
        }
        report[0] = SUPPLY + COMMA_SEPARATOR + totalSupply;
        report[1] = BUY + COMMA_SEPARATOR + totalBuy;
        report[2] = RESULT + COMMA_SEPARATOR + (totalSupply - totalBuy);
        return report;
    }

    public static void writeToFile(String[] report, String toFileName) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(toFileName, false))) {
            for (String part : report) {
                buffer.write(part);
                buffer.newLine();
                buffer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file.", e);
        }
    }

}
