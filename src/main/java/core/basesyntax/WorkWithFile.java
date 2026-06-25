package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private StringBuilder stringBuilder = new StringBuilder();

    private void processLine(String line, int[] sums) {
        String[] parts = line.split(COMMA);
        String type = parts[0];
        int amount = Integer.parseInt(parts[1]);
        if (type.equals(SUPPLY)) {
            sums[0] += amount;
        }
        if (type.equals(BUY)) {
            sums[1] += amount;
        }

    }

    private void writeResult(String fileName, int supplySum, int buySum) {
        int result = supplySum - buySum;
        stringBuilder.setLength(0);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write(SUPPLY + COMMA + supplySum);
            stringBuilder.append(SUPPLY + COMMA + supplySum);
            writer.newLine();

            writer.write(BUY + COMMA + buySum);
            stringBuilder.append(BUY + COMMA + buySum);
            writer.newLine();

            writer.write("result," + result);
            stringBuilder.append("result," + result);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Error while writing file: " + fileName, e);
        }
    }

    String getStatistic(String fromFileName, String toFileName) {
        int[] countersSum = new int[2]; // [0] = supply,[1] = buy
        List<String> allLines;

        try {
            allLines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file" + fromFileName, e);
        }
        for (String line : allLines) {
            processLine(line, countersSum);
        }
        writeResult(toFileName, countersSum[0], countersSum[1]);
        return stringBuilder.toString();
    }
}
