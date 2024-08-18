package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DEFAULT_SUM = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = calculateReport(data);
        writeIntoFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private String calculateReport(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = data.split(System.lineSeparator());
        int supplySum = DEFAULT_SUM;
        int buySum = DEFAULT_SUM;
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length == 2) {
                String operation = fields[0].trim();
                int amount = Integer.parseInt(fields[1].trim());
                if (operation.equals("supply")) {
                    supplySum += amount;
                } else if (operation.equals("buy")) {
                    buySum += amount;
                }
            }
        }
        int result = supplySum - buySum;
        stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeIntoFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(calculateReport(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
