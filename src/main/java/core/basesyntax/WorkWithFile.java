package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private int supplyAmount;
    private int buyAmount;
    private int resultAmount;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        calculateStatistics(fileContent);
        writeResult(supplyAmount, buyAmount, resultAmount, toFileName);
    }

    private String readFile(String fromFileName) {
        String line;
        StringBuilder resultBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                resultBuilder.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading a file", e);
        }

        return resultBuilder.toString().trim();
    }

    private void calculateStatistics(String unsortedStats) {
        supplyAmount = 0;
        buyAmount = 0;
        resultAmount = 0;

        String[] splitedStats = unsortedStats.split("[,\\r?\\n]+");
        for (int i = 1; i < splitedStats.length; i += 2) {
            String actionType = splitedStats[i - 1];
            int amount = Integer.parseInt(splitedStats[i]);

            if (actionType.equals(SUPPLY)) {
                supplyAmount += amount;
            }
            if (actionType.equals(BUY)) {
                buyAmount += amount;
            }
        }

        resultAmount = supplyAmount - buyAmount;
    }

    private void writeResult(int supplyAmount, int buyAmount, int resultAmount, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + COMMA + supplyAmount + System.lineSeparator());
            bufferedWriter.write(BUY + COMMA + buyAmount + System.lineSeparator());
            bufferedWriter.write("result," + resultAmount);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }
}
