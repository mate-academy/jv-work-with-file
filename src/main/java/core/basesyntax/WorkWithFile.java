package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int START_VALUE = 0;
    private static final int STEP = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyValue = calculateSupplyValue(readFromFile(fromFileName));
        int buyValue = calculateBuyValue(readFromFile(fromFileName));
        writeInfoToFile(supplyValue, buyValue, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(new File(fromFileName)))) {
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] listArray = line.split(",");
                for (String list : listArray) {
                    builder.append(list).append(",");
                }
                line = bufferedReader.readLine();
            }
            return builder.toString().split(",");
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + e);
        }
    }

    private int calculateSupplyValue(String[] string) {
        int supplyValue = START_VALUE;
        for (int i = 0; i < string.length; i++) {
            if (string[i].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(string[i + STEP]);
            }
        }
        return supplyValue;
    }

    private int calculateBuyValue(String[] string) {
        int buyValue = START_VALUE;
        for (int i = 0; i < string.length; i++) {
            if (string[i].equals(BUY)) {
                buyValue += Integer.parseInt(string[i + STEP]);
            }
        }
        return buyValue;
    }

    private void writeInfoToFile(int supply, int buy, String toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(toFile)))) {
            bufferedWriter.write(SUPPLY + "," + supply + System.lineSeparator()
                    + BUY + "," + buy + System.lineSeparator()
                    + RESULT + "," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't create this file " + e);
        }
    }

}
