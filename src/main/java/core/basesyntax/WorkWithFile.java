package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String[] data = report(fromFileName);
        writeToFile(data, toFileName);
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        try {
            List<String> list = Files.readAllLines(file.toPath());
            String[] result = new String[list.size()];
            for (int i = 0; i < result.length; i++) {
                result [i] = list.get(i);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("can't read from the file" + fileName, e);
        }
    }

    private void writeToFile(String[] data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String array : data) {
                bufferedWriter.write(array + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("can't write to the " + toFileName, e);
        }
    }

    private String[] report(String fromFileName) {
        final String separator = ",";
        final int valueIndex = 1;
        final int operationIndex = 0;
        final String supply = "supply,";
        final String buy = "buy,";
        final String result = "result,";
        String[] arrayFromFile = readFromFile(fromFileName);
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : arrayFromFile) {
            String[] parts = line.split(separator);
            int value = Integer.parseInt(parts[valueIndex]);
            if ("supply".equals(parts[operationIndex])) {
                totalSupply += value;
            } else if ("buy".equals(parts[operationIndex])) {
                totalBuy += value;
            }
        }

        return new String[] {supply + totalSupply,
                buy + totalBuy, result + (totalSupply - totalBuy)};
    }
}
