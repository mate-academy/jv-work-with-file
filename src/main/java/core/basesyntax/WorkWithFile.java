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

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        File file = new File(fromFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            String[] temp;
            while (value != null) {
                temp = value.split(",");
                String operationType = temp[INDEX_OF_OPERATION_TYPE];
                int amount = Integer.parseInt(temp[INDEX_OF_AMOUNT]);
                if (operationType.equals("supply")) {
                    supplyAmount += amount;
                } else if (operationType.equals("buy")) {
                    buyAmount += amount;
                }

                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        StringBuilder resultString = new StringBuilder();
        resultString.append(NAME_OF_OPERATION_SUPPLY).append(",").append(supplyAmount)
                .append(System.lineSeparator());

        resultString.append(NAME_OF_OPERATION_BUY).append(",").append(buyAmount)
                .append(System.lineSeparator());
        int resultAmount = supplyAmount - buyAmount;
        resultString.append("result,").append(resultAmount).append(System.lineSeparator());

        File fileToWrite = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(resultString.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
