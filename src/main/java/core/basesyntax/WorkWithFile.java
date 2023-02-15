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
            String input = bufferedReader.readLine();
            int totalBuy = 0;
            int totalSupply = 0;
            while (input != null) {
                String[] inputArray = input.split(",");
                if (inputArray.length < DATA_LENGTH) {
                    throw new RuntimeException("Invalid data format");
                } else if (inputArray[INDEX_DATA_OPERATION_TYPE].equals("buy")) {
                    totalBuy += Integer.parseInt(inputArray[INDEX_AMOUNT]);
                } else if (inputArray[INDEX_DATA_OPERATION_TYPE].equals("supply")) {
                    totalSupply += Integer.parseInt(inputArray[INDEX_AMOUNT]);
                }
                input = bufferedReader.readLine();
            }
            return new int[] {totalSupply, totalBuy};
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeDataToFile(int[] totalData, String toFileName) {
        String report = createReport(totalData);
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }

    private String createReport(int[] totalData) {
        int supplySum = totalData[SUPPLY_INDEX_IN_ARRAY];
        int buySum = totalData[BUY_INDEX_IN_ARRAY];
        int result = supplySum - buySum;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_ACTION).append(',').append(supplySum)
            .append(System.lineSeparator());
        report.append(BUY_ACTION).append(',').append(buySum).append(System.lineSeparator());
        report.append(RESULT_ACTION).append(',').append(result);
        return report.toString();
    }
}
