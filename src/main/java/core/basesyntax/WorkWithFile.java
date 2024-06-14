package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int START_VALUE = 0;
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeInfoToFile(calculateSupplyValue(readFromFile(fromFileName)),
                calculateBuyValue(readFromFile(fromFileName)), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(COMA);
                line = bufferedReader.readLine();
            }
            return builder.toString().split(COMA);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName + e.getMessage());
        }
    }

    private int calculateSupplyValue(String[] dataLines) {
        int supplyValue = START_VALUE;
        for (int i = 0; i < dataLines.length; i++) {
            if (dataLines[i].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(dataLines[i + 1]);
            }
        }
        return supplyValue;
    }

    private int calculateBuyValue(String[] dataLines) {
        int buyValue = START_VALUE;
        for (int i = 0; i < dataLines.length; i++) {
            if (dataLines[i].equals(BUY)) {
                buyValue += Integer.parseInt(dataLines[i + 1]);
            }
        }
        return buyValue;
    }

    private void writeInfoToFile(int supply, int buy, String toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(SUPPLY + "," + supply + System.lineSeparator()
                    + BUY + "," + buy + System.lineSeparator()
                    + RESULT + "," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write down to the file " + toFile + e.getMessage());
        }
    }
}
