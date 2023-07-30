package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] fromFileContent = readFile(fromFileName);
        String reportData = reportDataGenerator(fromFileContent);
        writeToFile(toFileName, reportData);
    }

    private String[] readFile(String fileName) {
        File file = new File(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                builder.append(line).append(";");
                line = bufferedReader.readLine();
            }

            String fileContent = builder.toString();

            if (fileContent.length() != 0) {
                return fileContent.split(";");
            } else {
                return new String[0];
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fileName, e);
        }
    }

    private void writeToFile(String fileName, String reportData) {
        File file = new File(fileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + fileName, e);
        }
    }

    private String reportDataGenerator(String[] input) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (int i = 0; i < input.length; i++) {
            String[] record = input[i].split(",");
            String key = record[0];
            int value = Integer.parseInt(record[1]);

            if (key.equals("supply")) {
                supplyTotal += value;
            } else if (key.equals("buy")) {
                buyTotal += value;
            }
        }

        int result = supplyTotal - buyTotal;

        StringBuilder builder = new StringBuilder();
        builder.append("supply").append(",").append(supplyTotal).append(System.lineSeparator())
                .append("buy").append(",").append(buyTotal).append(System.lineSeparator())
                .append("result").append(",").append(result).append(System.lineSeparator());
        String reportData = builder.toString();

        return reportData;
    }
}
