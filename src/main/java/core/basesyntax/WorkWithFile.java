package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = System.lineSeparator();
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String SEPARATOR = ",";
    private static final String RESULT = "result";
    private static final int ACTION_NAME_INDEX = 0;
    private static final int ACTION_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                data.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }
        return data.toString();
    }

    private String generateReport(String data) {
        String[] names = data.toString().split(DELIMITER);
        int sumSupply = 0;
        int sumBuy = 0;
        for (String name : names) {
            String[] results = name.split(SEPARATOR);
            if (results[ACTION_NAME_INDEX].equals(SUPPLY)) {
                int sum = Integer.parseInt(results[ACTION_VALUE_INDEX]);
                sumSupply += sum;
            }
            if (results[ACTION_NAME_INDEX].equals(BUY)) {
                int sum = Integer.parseInt(results[ACTION_VALUE_INDEX]);
                sumBuy += sum;
            }
        }
        return SUPPLY + SEPARATOR + sumSupply + System.lineSeparator()
                + BUY + SEPARATOR + sumBuy + System.lineSeparator()
                + RESULT + SEPARATOR + (sumSupply - sumBuy);
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
