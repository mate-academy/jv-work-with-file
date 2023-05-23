package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = getSumOfNumber(getResultFromFileByWord(fromFileName, "supply"));
        int buySum = getSumOfNumber(getResultFromFileByWord(fromFileName, "buy"));
        int result = supplySum - buySum;

        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't delete a file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write("supply," + supplySum + System.lineSeparator()
                    + "buy," + buySum + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }

    private String[] getResultFromFileByWord(String file, String worldForSearch) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(worldForSearch)) {
                    builder.append(line).append(" ");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString().split(" ");
    }

    private int getSumOfNumber(String[] array) {
        int sum = 0;

        for (String value: array) {
            sum += Integer.parseInt(value.replaceAll("[^0-9]", ""));
        }
        return sum;
    }
}
