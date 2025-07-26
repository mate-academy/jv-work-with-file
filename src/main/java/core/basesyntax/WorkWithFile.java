package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FILE_NAME_INDEX = 0;
    private static final int FILE_PRICE_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String BUY_TYPE = "buy";
    private static final String SUPPLY_TYPE = "supply";
    private static final String RESULT_TYPE = "result";
    private static final String SPLIT_SYMBOL = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readData(fromFileName);
        int totalSupply = totals[SUPPLY_INDEX];
        int totalBuy = totals[BUY_INDEX];

        String report = generateReport(totalSupply, totalBuy);
        writeToFile(toFileName, report);
    }

    private int[] readData(String fromFileName) {
        int[] data = new int[2];
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split(SPLIT_SYMBOL);
                if (lineArray[FILE_NAME_INDEX].equalsIgnoreCase(SUPPLY_TYPE)) {
                    data[SUPPLY_INDEX] += Integer.parseInt(lineArray[FILE_PRICE_INDEX]);
                } else if (lineArray[FILE_NAME_INDEX].equalsIgnoreCase(BUY_TYPE)) {
                    data[BUY_INDEX] += Integer.parseInt(lineArray[FILE_PRICE_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file which not exist.");
        }
        return data;

    }

    private String generateReport(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_TYPE).append(SPLIT_SYMBOL).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY_TYPE).append(SPLIT_SYMBOL).append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT_TYPE).append(SPLIT_SYMBOL).append(result);

        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: ", e);
        }
    }
}

