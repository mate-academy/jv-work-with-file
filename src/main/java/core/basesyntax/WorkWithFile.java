package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FILE_NAME = 0;
    private static final int RESULT_NAME = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String result = calculateDate(data);
        writeDateToFile(toFileName, result);
    }

    private String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return builder.toString();
    }

    private String calculateDate(String data) {
        StringBuilder builder = new StringBuilder();
        String[] line = data.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String lines : line) {
            String[] parts = lines.split(",");
            if (parts[FILE_NAME].equals("supply")) {
                supplySum += Integer.parseInt(parts[RESULT_NAME]);
            } else if (parts[FILE_NAME].equals("buy")) {
                buySum += Integer.parseInt(parts[RESULT_NAME]);
            }
        }
        builder.append("supply,").append(supplySum)
                .append(System.lineSeparator())
                .append("buy,").append(buySum)
                .append(System.lineSeparator())
                .append("result,").append(supplySum - buySum);
        return builder.toString();
    }

    private void writeDateToFile(String toFile, String result) {
        File newFile = new File(toFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
