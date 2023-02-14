package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_ACTION = "supply";
    private static final String BUY_ACTION = "buy";
    private static final String RESULT_ACTION = "result";
    private static final int INDEX_DATA_OPERATION_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;
    private static final int DATA_LENGTH = 2;
    private static final int SUPPLY_INDEX_IN_ARRAY = 0;
    private static final int BUY_INDEX_IN_ARRAY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totalData = getTotalDataFromFile(fromFileName);
        writeDataToFile(totalData, toFileName);
    }

    private int[] getTotalDataFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int supplyActionsTotal = 0;
            int buyActionsTotal = 0;
            String data = bufferedReader.readLine();
            while (data != null) {
                String[] dataArray = data.split(",");
                if (dataArray.length != DATA_LENGTH) {
                    throw new RuntimeException("Invalid data format");
                } else if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(SUPPLY_ACTION)) {
                    supplyActionsTotal++;
                } else if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(BUY_ACTION)) {
                    buyActionsTotal++;
                }
                data = bufferedReader.readLine();
            }
            try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file))) {
                int[] supplyArray = new int[supplyActionsTotal];
                int[] buyArray = new int[buyActionsTotal];
                int supplyCount = 0;
                int buyCount = 0;
                data = bufferedReader1.readLine();
                while (data != null) {
                    String[] dataArray = data.split(",");
                    if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(SUPPLY_ACTION)) {
                        supplyArray[supplyCount] = Integer.parseInt(dataArray[INDEX_AMOUNT]);
                        supplyCount++;
                    } else if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(BUY_ACTION)) {
                        buyArray[buyCount] = Integer.parseInt(dataArray[INDEX_AMOUNT]);
                        buyCount++;
                    }
                    data = bufferedReader1.readLine();
                }
                int supplyTotal = 0;
                int buyTotal = 0;
                for (int buy: buyArray) {
                    buyTotal += buy;
                }
                for (int supply: supplyArray) {
                    supplyTotal += supply;
                }
                int[] result = {supplyTotal, buyTotal};
                return result;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from " + fromFileName, e);
        }
    }

    private void writeDataToFile(int[] totalData, String toFileName) {
        File file = new File(toFileName);
        int supplySum = totalData[SUPPLY_INDEX_IN_ARRAY];
        int buySum = totalData[BUY_INDEX_IN_ARRAY];
        int result = supplySum - buySum;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_ACTION).append(',').append(supplySum)
            .append(System.lineSeparator());
        report.append(BUY_ACTION).append(',').append(buySum).append(System.lineSeparator());
        report.append(RESULT_ACTION).append(',').append(result);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }
}
