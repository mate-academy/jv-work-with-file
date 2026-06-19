package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {

    void processLine(String line, int[] sums) {
        String[] parts = line.split(",");
        String type = parts[0];
        int amount = Integer.parseInt(parts[1]);
        if (type.equals("supply")) {
            sums[0] += amount;
        }
        if (type.equals("buy")) {
            sums[1] += amount;
        }

    }

    private void writeResult(String fileName, int supplySum, int buySum) {
        int result = supplySum - buySum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write("supply," + supplySum);
            writer.newLine();

            writer.write("buy," + buySum);
            writer.newLine();

            writer.write("result," + result);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error while writing file: " + e.getMessage());
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] countersSum = new int[2]; // [0] = supply, [1] = buy
        List<String> allLines;
        try {
            allLines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
        for (String line : allLines) {
            processLine(line, countersSum);
        }
        writeResult(toFileName, countersSum[0], countersSum[1]);
    }
}
