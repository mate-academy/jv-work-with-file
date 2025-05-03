package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String result = createReport(data);
        writeToFile(toFileName, result);
    }

    private String[] readFile(String fromFileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return lines.toArray(new String[0]);
    }

    private String createReport(String[] data) {
        StringBuilder builder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;

        for (String item : data) {
            String[] parts = item.split(",");
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

            if (item.startsWith("supply")) {
                supplySum += amount;
            }

            if (item.startsWith("buy")) {
                buySum += amount;
            }
        }
        int result = supplySum - buySum;

        return builder.append("supply,").append(supplySum)
                .append(System.lineSeparator())
                .append("buy,").append(buySum)
                .append(System.lineSeparator())
                .append("result,").append(result)
                .append(System.lineSeparator()).toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
