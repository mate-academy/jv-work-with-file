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
    private static final String SEPARATOR = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        int[] operationTotals = calculateTotals(lines);
        String[] resultsLines = getResultLines(operationTotals);
        writeToFile(resultsLines, toFileName);
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
            String[] lineArray = line.split(SEPARATOR);
            String operationType = lineArray[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(lineArray[AMOUNT_INDEX]);

            if (OPERATION_SUPPLY.equals(operationType)) {
                supplyTotal += amount;
            } else if (OPERATION_BUY.equals(operationType)) {
                buyTotal += amount;
            }
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private String[] getResultLines(int[] summaryArray) {
        String[] resultLines = new String[3];

        resultLines[0] = OPERATION_SUPPLY + SEPARATOR + summaryArray[0];
        resultLines[1] = OPERATION_BUY + SEPARATOR + summaryArray[1];
        resultLines[2] = "result" + SEPARATOR + (summaryArray[0] - summaryArray[1]);

        return resultLines;

    }

    private void writeToFile(String[] resultsLines, String toFileName) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            for (String line : resultsLines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }

}

