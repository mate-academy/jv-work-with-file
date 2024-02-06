package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;
    private static final int NULL_AMOUNT = 0;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "s";
    private static final String BUY_OPERATION = "b";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] result = calculateReport(fromFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + result[0] + System.lineSeparator()
                    + "buy," + result[1] + System.lineSeparator()
                    + "result," + result[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private int[] calculateReport(String fromFileName) {
        int supplyAmount = NULL_AMOUNT;
        int buyAmount = NULL_AMOUNT;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int[] amounts = separateSum(line);
                supplyAmount += amounts[0];
                buyAmount += amounts[1];
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        int result = supplyAmount - buyAmount;
        return new int[]{supplyAmount, buyAmount, result};
    }

    private int[] separateSum(String line) {
        String[] words = line.split(SEPARATOR);
        String operationType = words[FIRST_PART];
        String amount = words[SECOND_PART];
        int supplyAmount = 0;
        int buyAmount = 0;
        if (operationType.startsWith(SUPPLY_OPERATION)) {
            supplyAmount = Integer.parseInt(amount);
        }
        if (operationType.startsWith(BUY_OPERATION)) {
            buyAmount = Integer.parseInt(amount);
        }
        return new int[]{supplyAmount, buyAmount};
    }
}
