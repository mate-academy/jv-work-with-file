package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_OPTION = "buy";
    private static final String SUPPLY_OPTION = "supply";
    private static final String RESULT_OPTION = "result";
    private static final int OPTION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int AMOUNT_OPTIONS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        int sumSupply = calculateSum(data, SUPPLY_OPTION);
        int sumBuy = calculateSum(data, BUY_OPTION);
        int result = sumSupply - sumBuy;
        String report = createReport(sumSupply, sumBuy, result);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            return bufferedReader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fileName, e);
        }
    }

    private int calculateSum(String[] data, String option) {
        int sum = 0;
        for (String line : data) {
            String[] parts = line.split(",");
            if (parts.length == AMOUNT_OPTIONS && parts[OPTION_INDEX].equalsIgnoreCase(option)) {
                sum += Integer.parseInt(parts[AMOUNT_INDEX]);
            }
        }
        return sum;
    }

    private String createReport(int sumSupply, int sumBuy, int result) {
        return SUPPLY_OPTION + "," + sumSupply + System.lineSeparator()
                + BUY_OPTION + "," + sumBuy + System.lineSeparator()
                + RESULT_OPTION + "," + result;
    }

    private void writeToFile(String path, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + path, e);
        }
    }
}
