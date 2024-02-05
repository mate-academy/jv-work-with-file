package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;
    private static final int NULL_AMOUNT = 0;
    private int supplyAmount = 0;
    private int buyAmount = 0;
    private String line = null;

    public void getStatistic(String fromFileName, String toFileName) {
        calculateReport(fromFileName, toFileName);
        anulatePreviousResult();

    }

    private void calculateReport(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyAmount + System.lineSeparator()
                    + "buy," + buyAmount + System.lineSeparator()
                    + "result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                separate_sum();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void separate_sum() {
        String[] words = line.split(SEPARATOR);
        String wordPart = words[FIRST_PART];
        String numberPart = words[SECOND_PART];
        if (wordPart.startsWith("s")) {
            supplyAmount += Integer.parseInt(numberPart);
        }
        if (wordPart.startsWith("b")) {
            buyAmount += Integer.parseInt(numberPart);
        }
    }

    private void anulatePreviousResult() {
        supplyAmount = NULL_AMOUNT;
        buyAmount = NULL_AMOUNT;
    }
}
