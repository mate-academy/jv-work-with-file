package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String COMA_SEPARATOR = ",";
    private static final String SUPPLY_WORD = "supply";
    private static String BUY_WORD = "buy";
    private static int OPERATION_TYPE_INDEX = 0;
    private static int AMOUNT_INDEX = 1;
    private static final int SUPPLY_AMOUNT_INDEX = 0;
    private static final int BUY_AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(calculateStatistic(readFromFile(fromFileName))));
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader inputFile = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = inputFile.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
            String content = builder.toString();
            return content.split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from file" + fromFileName, e);
        }
    }

    private int[] calculateStatistic(String[] contentArray) {
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String string : contentArray) {
            if (string.split(COMA_SEPARATOR)[OPERATION_TYPE_INDEX].equals(SUPPLY_WORD)) {
                supplyTotal += Integer.parseInt(string.split(COMA_SEPARATOR)[AMOUNT_INDEX]);
            } else if (string.split(COMA_SEPARATOR)[OPERATION_TYPE_INDEX].equals(BUY_WORD)) {
                buyTotal += Integer.parseInt(string.split(COMA_SEPARATOR)[AMOUNT_INDEX]);
            }
        }
        return new int[]{supplyTotal, buyTotal};
    }

    private String createReport(int[] totals) {
        final int result = totals[SUPPLY_AMOUNT_INDEX] - totals[BUY_AMOUNT_INDEX];
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(totals[SUPPLY_AMOUNT_INDEX]).append(System.lineSeparator())
              .append("buy,").append(totals[BUY_AMOUNT_INDEX]).append(System.lineSeparator())
              .append("result,").append(result).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(toFileName))) {
            outputFile.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write report to file" + toFileName, e);
        }
    }
}

