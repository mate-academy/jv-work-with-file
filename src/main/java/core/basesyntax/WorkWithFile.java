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
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeResult(generateReport(readFile(fromFileName)), toFileName);
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

    private String generateReport(String unsortedStats) {
        int supplyAmount = 0;
        int buyAmount = 0;
        int resultAmount = 0;

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

        return SUPPLY + COMMA + supplyAmount + System.lineSeparator()
                + BUY + COMMA + buyAmount + System.lineSeparator()
                + RESULT + COMMA + resultAmount;
    }

    private void writeResult(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }
}
