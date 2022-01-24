package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                int amount = Integer.parseInt(
                        currentLine.substring(currentLine.indexOf(REGEX) + 1));
                if (currentLine.startsWith(BUY)) {
                    buyAmount += amount;
                }
                if (currentLine.startsWith(SUPPLY)) {
                    supplyAmount += amount;
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + e);
        }
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
}
