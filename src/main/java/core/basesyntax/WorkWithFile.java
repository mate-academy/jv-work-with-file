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
    private static final String SEPARATOR = ",";
    private static final int TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int TOTAL_BUY_SUPPLY = 2;

    private static final int BUY_INDEX = 0;
    private static final int SUPPLY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(calculate(readFromFile(fromFileName))));
    }

    private String createReport(int[] totalBuySupply) {
        StringBuilder info = new StringBuilder();
        info.append(SUPPLY).append(SEPARATOR).append(totalBuySupply[SUPPLY_INDEX])
                .append(System.lineSeparator()).append(BUY).append(SEPARATOR)
                .append(totalBuySupply[BUY_INDEX]).append(System.lineSeparator()).append(RESULT)
                .append(SEPARATOR).append(totalBuySupply[SUPPLY_INDEX] - totalBuySupply[BUY_INDEX]);
        return info.toString();
    }

    private String readFromFile(String fileName) {
        StringBuilder readLines = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readLines.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fileName, e);
        }
        return readLines.toString();
    }

    private int[] calculate(String readLines) {
        int[] totalBuySupply = new int[TOTAL_BUY_SUPPLY];
        int totalBuy = 0;
        int totalSupply = 0;
        String[] data = readLines.split(System.lineSeparator());
        for (String line : data) {
            String[] type = line.split(SEPARATOR);
            if (type[TYPE_INDEX].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(type[AMOUNT_INDEX]);
            }
            if (type[TYPE_INDEX].equals(BUY)) {
                totalBuy += Integer.parseInt(type[AMOUNT_INDEX]);
            }
        }
        totalBuySupply[BUY_INDEX] = totalBuy;
        totalBuySupply[SUPPLY_INDEX] = totalSupply;
        return totalBuySupply;
    }

    private void writeToFile(String toFile, String info) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(info);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFile, e);
        }
    }
}
