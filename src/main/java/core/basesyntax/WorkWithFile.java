package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] sums = readFile(fromFileName);
        String report = generateReport(sums[0], sums[1]);
        writeToFile(toFileName, report);
    }

    private int[] readFile(String fileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    supplySum += amount;
                } else if (operation.equals("buy")) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fileName, e);
        }
        return new int[]{supplySum, buySum};
    }

    private String generateReport(int supplySum, int buySum) {
        int result = supplySum - buySum;
        StringBuilder report = new StringBuilder();

        report.append("supply,").append(supplySum).append(System.lineSeparator());
        report.append("buy,").append(buySum).append(System.lineSeparator());
        report.append("result,").append(result).append(System.lineSeparator());

        return report.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + fileName, e);
        }
    }
}
