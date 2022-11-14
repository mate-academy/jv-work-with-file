package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File reportFile = new File(toFileName);
        String[] data = getDataFromFile(fromFileName);
        String report = createReport(data);
        writeDataToFile(reportFile, report);
    }

    private String[] getDataFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split("\\W+");
    }

    private int getAmount(String operation, String[] data) {
        int amount = 0;
        for (int i = 0; i < data.length; i += 2) {
            if (data[i].equals(operation)) {
                amount += Integer.parseInt(data[i + 1]);
            }
        }
        return amount;
    }

    private String createReport(String[] data) {
        StringBuilder report = new StringBuilder();
        int amountSupply = getAmount(OPERATION_SUPPLY, data);
        int amountBuy = getAmount(OPERATION_BUY, data);
        report.append(OPERATION_SUPPLY).append(',')
                .append(amountSupply).append(System.lineSeparator())
                .append(OPERATION_BUY).append(',')
                .append(amountBuy).append(System.lineSeparator())
                .append(RESULT).append(',')
                .append(amountSupply - amountBuy);
        return report.toString();
    }

    private void writeDataToFile(File file, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
