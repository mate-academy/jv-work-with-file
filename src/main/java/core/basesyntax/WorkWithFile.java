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
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return data.toString();
    }

    private String createReport(String data) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            String operationType = parts[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);
            if (operationType.equals(SUPPLY)) {
                totalSupply += amount;
            } else if (operationType.equals(BUY)) {
                totalBuy += amount;
            }
        }
        int result = totalSupply - totalBuy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(totalSupply).append(System.lineSeparator());
        report.append(BUY).append(",").append(totalBuy).append(System.lineSeparator());
        report.append(RESULT).append(",").append(result);
        return report.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
