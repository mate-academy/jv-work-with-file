package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        int[] operationTotals = calculateTotals(lines);
        writeToFile(operationTotals, toFileName);
    }

    private List<String> readLinesFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        List<String> lines;

        try {
            lines = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }

        return lines;
    }

    private int[] calculateTotals(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] lineArray = line.split(",");
            String operationType = lineArray[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(lineArray[AMOUNT_INDEX]);

            if ("supply".equals(operationType)) {
                supplyTotal += amount;
            } else if ("buy".equals(operationType)) {
                buyTotal += amount;
            }
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private void writeToFile(int[] summaryArray, String toFileName) {
        int netResult = summaryArray[0] - summaryArray[1];

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + summaryArray[0]);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + summaryArray[1]);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + netResult);

        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }
}
