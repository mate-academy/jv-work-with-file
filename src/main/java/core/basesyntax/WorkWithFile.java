package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int POSITION_TRAN_TYPE = 0;
    private static final int POSITION_AMOUNT = 1;

    static void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readData(fromFileName);
        String results = calculateResults(fileContent);
        writeResults(toFileName,results);
    }

    private static String readData(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
        return content.toString();
    }

    private static String calculateResults(String fileContent) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            String transactionType = parts[POSITION_TRAN_TYPE];
            int transactionAmount;
            try {
                transactionAmount = Integer.parseInt(parts[POSITION_AMOUNT].trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Can't parse amount " + parts[POSITION_AMOUNT], e);
            }
            totalSupply += SUPPLY.equals(transactionType) ? transactionAmount : 0;
            totalBuy += BUY.equals(transactionType) ? transactionAmount : 0;
        }
        int result = totalSupply - totalBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(DELIMITER).append(totalSupply).append(System.lineSeparator());
        builder.append(BUY).append(DELIMITER).append(totalBuy).append(System.lineSeparator());
        builder.append(RESULT).append(DELIMITER).append(result).append(System.lineSeparator());
        return builder.toString();
    }

    private static void writeResults(String toFileName, String results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(results);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
