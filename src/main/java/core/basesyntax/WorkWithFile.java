package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int AMOUNT_INDEX = 1;
    private static final int AMOUNT_OF_OPERATIONS = 2;
    private static final String LINE_SPLITERATOR = "/";
    private static final String SPLITERATOR = ",";
    private static final String SUPPLY_LINE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReportString(createReport(readFromFile(fromFileName))));
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(LINE_SPLITERATOR);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file " + fromFileName, e);
        }
    }

    private int[] createReport(String readFromFile) {
        int[] sums = new int[AMOUNT_OF_OPERATIONS];
        String[] fileLines = readFromFile.split(LINE_SPLITERATOR);

        for (String fileLine : fileLines) {
            if (fileLine.contains(SUPPLY_LINE)) {
                String[] supplyLine = fileLine.split(SPLITERATOR);
                sums[SUPPLY_INDEX] += Integer.parseInt(supplyLine[AMOUNT_INDEX]);
            } else {
                String[] buyLine = fileLine.split(SPLITERATOR);
                sums[BUY_INDEX] += Integer.parseInt(buyLine[AMOUNT_INDEX]);
            }
        }
        return sums;
    }

    private String createReportString(int[] sums) {
        int result = sums[SUPPLY_INDEX] - sums[BUY_INDEX];
        StringBuilder stringBuilder = new StringBuilder("supply,");
        return stringBuilder.append(sums[SUPPLY_INDEX]).append(System.lineSeparator())
                .append("buy,").append(sums[BUY_INDEX]).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private void writeToFile(String file, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file " + file, e);
        }
    }
}
