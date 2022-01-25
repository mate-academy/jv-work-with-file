package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String REGEX = ",";

    private int supplyAmount = 0;
    private int buyAmount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName, createReport());
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = bufferedReader.readLine();

            while (currentLine != null) {
                countData(currentLine);
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file `" + fromFileName + "`" + e);
        }
    }

    private void countData(String currentLine) {
        int amount = Integer.parseInt(
                currentLine.substring(currentLine.indexOf(REGEX) + 1));
        if (currentLine.startsWith(BUY)) {
            buyAmount += amount;
        }
        if (currentLine.startsWith(SUPPLY)) {
            supplyAmount += amount;
        }
    }

    private String createReport() {
        return SUPPLY + REGEX + supplyAmount + System.lineSeparator() +
                BUY + REGEX + buyAmount + System.lineSeparator() +
                RESULT + REGEX + (supplyAmount - buyAmount);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e);
        }
    }
}
