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

    public String readFile(String fileName) {
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

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        String value = readFile(fromFileName);
        String[] line = value.split(System.lineSeparator());
        for (String lines : line) {
            String[] parts = lines.split(",");
            if (parts[FILE_NAME].equals("supply")) {
                supplySum = supplySum + Integer.parseInt(parts[RESULT_NAME]);
            } else if (parts[FILE_NAME].equals("buy")) {
                buySum += Integer.parseInt(parts[RESULT_NAME]);
            }
        }
        File newFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write("supply," + supplySum);
            writer.newLine();
            writer.write("buy," + buySum);
            writer.newLine();
            writer.write("result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
