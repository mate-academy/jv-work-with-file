package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NAME_OF_OPERATION_SUPPLY = "supply";
    private static final String NAME_OF_OPERATION_BUY = "buy";
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int INDEX_OF_SUPPLY_AMOUNT = 0;
    private static final int INDEX_OF_BUY_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        int[] operationsAmounts = readAmountsOfOperations(fromFileName);
        supplyAmount = operationsAmounts[INDEX_OF_SUPPLY_AMOUNT];
        buyAmount = operationsAmounts[INDEX_OF_BUY_AMOUNT];

        String report = createReportMessage(supplyAmount, buyAmount);
        File fileToWrite = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private static int[] readAmountsOfOperations(String fromFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        File file = new File(fromFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            String[] lineFromInputfile;
            while (value != null) {
                lineFromInputfile = value.split(",");
                String operationType = lineFromInputfile[INDEX_OF_OPERATION_TYPE];
                int amount = Integer.parseInt(lineFromInputfile[INDEX_OF_AMOUNT]);
                if (operationType.equals(NAME_OF_OPERATION_SUPPLY)) {
                    supplyAmount += amount;
                } else if (operationType.equals(NAME_OF_OPERATION_BUY)) {
                    buyAmount += amount;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supplyAmount, buyAmount};
    }

    private String createReportMessage(int supplyAmount, int buyAmount) {
        int resultAmount = supplyAmount - buyAmount;
        return NAME_OF_OPERATION_SUPPLY + "," + supplyAmount + System.lineSeparator()
                + NAME_OF_OPERATION_BUY + "," + buyAmount + System.lineSeparator()
                + "result," + resultAmount + System.lineSeparator();
    }
}
