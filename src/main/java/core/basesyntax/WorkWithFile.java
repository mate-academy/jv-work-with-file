package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int buyAmount = 0;
        int supplyAmount = 0;
        File file = new File(fromFileName);
        StringBuilder str = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                str.append((char) value);
                value = bufferedReader.read();
            }
            String[] rows = str.toString().split("\\r?\\n");
            for (String row : rows) {
                if (row.isEmpty()) {
                    continue;
                }
                String[] rowArray = row.split(",");
                if (rowArray.length < 2) {
                    continue;
                }
                String operation = rowArray[0];
                int amount = Integer.parseInt(rowArray[1]);
                if (operation.equals(BUY)) {
                    buyAmount += amount;
                } else {
                    supplyAmount += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            String lineSeparator = System.lineSeparator();
            fileWriter.append(SUPPLY).append(",").append(String.valueOf(supplyAmount))
                    .append(lineSeparator).append(BUY).append(",")
                    .append(String.valueOf(buyAmount)).append(lineSeparator)
                    .append("result,").append(String.valueOf(supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }

    public static void main(String[] args) {
        String fromFileName = "apple.csv";
        String toFileName = "result.txt";
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(fromFileName, toFileName);
    }
}
