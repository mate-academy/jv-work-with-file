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
    private static final String SEPARATE = ",";
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder read = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                read.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
        return read.toString();
    }

    private String generateReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] files = line.split(SEPARATE);
            if (files[OPERATION].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(files[AMOUNT]);
            } else if (files[OPERATION].equals(BUY)) {
                buyTotal += Integer.parseInt(files[AMOUNT]);
            }
        }
        return SUPPLY + SEPARATE + supplyTotal
                +
                System.lineSeparator()
                +
                BUY + SEPARATE + buyTotal
                +
                System.lineSeparator()
                +
                RESULT + SEPARATE + (supplyTotal - buyTotal);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
