package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    private static final String SEMICOLON = ";";
    private static final String COMMA = ",";

    private static final int COLUMNS_COUNT = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromCsvFile(fromFileName);
        String[][] table = createTable(fileContent);
        String data = generateReport(table);
        writeToFile(toFileName, data);
    }

    private static String generateReport(String[][] table) {
        int totalBuy = 0;
        int totalSupply = 0;

        for (String[] row : table) {
            String operation = row[0];
            int value = Integer.parseInt(row[1]);

            if (SUPPLY.equals(operation)) {
                totalSupply = totalSupply + value;
            } else if (BUY.equals(operation)) {
                totalBuy = totalBuy + value;
            }
        }
        
        int total = totalSupply - totalBuy;

        return buildDataRow(SUPPLY, totalSupply) + System.lineSeparator()
                + buildDataRow(BUY, totalBuy) + System.lineSeparator()
                + buildDataRow(RESULT, total);
    }

    private static String buildDataRow(String operationType, int value) {
        return operationType + COMMA + value;
    }

    private static String[][] createTable(String string) {
        String[] rows = string.split(SEMICOLON);
        String[][] table = new String[rows.length][COLUMNS_COUNT];
        for (int i = 0; i < rows.length; i++) {
            table[i] = rows[i].split(COMMA);
        }

        return table;
    }

    private static String readFromCsvFile(String filename) {
        File file = new File(filename);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value);
                stringBuilder.append(SEMICOLON);
                value = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Can't read file", ex);
        }

        return stringBuilder.toString();
    }

    private static void writeToFile(String filename, String data) {
        File file = new File(filename);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write to file", ex);
        }
    }
}
