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

        StringBuilder buyString = new StringBuilder(BUY)
                .append(REGEX).append(buyAmount).append(System.lineSeparator());
        StringBuilder supplyString = new StringBuilder(SUPPLY)
                .append(REGEX).append(supplyAmount).append(System.lineSeparator());
        StringBuilder resultString = new StringBuilder(RESULT)
                .append(REGEX).append(supplyAmount - buyAmount);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(supplyString.toString());
            bufferedWriter.write(buyString.toString());
            bufferedWriter.write(resultString.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e);
        }
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
}
