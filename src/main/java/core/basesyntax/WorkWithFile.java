package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLines(fromFileName);
        int[] sums = calculateSums(lines);
        writeReport(toFileName, sums[0], sums[1]);
    }

    private List<String> readLines(String fileName) {
        File file = new File(fileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

    private int[] calculateSums(List<String> lines) {
        int supplySum = 0;
        int buySum = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                continue; // на всякий случай, если строка криво форматирована
            }

            String type = parts[0].trim();
            int amount = Integer.parseInt(parts[1].trim());

            if (SUPPLY.equals(type)) {
                supplySum += amount;
            } else if (BUY.equals(type)) {
                buySum += amount;
            }
        }

        return new int[]{supplySum, buySum};
    }

    private void writeReport(String fileName, int supplySum, int buySum) {
        String report = SUPPLY + "," + supplySum + "\n"
                + BUY + "," + buySum + "\n"
                + "result," + (supplySum - buySum);

        File file = new File(fileName);
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + fileName, e);
        }
    }
}
