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
    private static final int AMOUNT_OPTIONS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String[] data = readFromFile(fromFileName);
            int sumSupply = calculateSum(data, SUPPLY_OPTION);
            int sumBuy = calculateSum(data, BUY_OPTION);
            int result = sumSupply - sumBuy;
            createReport(toFileName, sumSupply, sumBuy, result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
    }

    private String[] readFromFile(String fileName) throws IOException {
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
            if (parts.length == AMOUNT_OPTIONS && parts[0].equalsIgnoreCase(option)) {
                sum += Integer.parseInt(parts[1]);
            }
        }
        return sum;
    }

    private void createReport(String fileName, int sumSupply, int sumBuy, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            writeToFile(bufferedWriter, SUPPLY_OPTION, sumSupply);
            writeToFile(bufferedWriter, BUY_OPTION, sumBuy);
            writeToFile(bufferedWriter, RESULT_OPTION, result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileName, e);
        }
    }

    private void writeToFile(BufferedWriter bufferedWriter, String option, int value) {
        try {
            bufferedWriter.write(option + "," + value);
            if (!option.equals(RESULT_OPTION)) {
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
