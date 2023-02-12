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

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            int[] supplies = getSuppliesFromFile(fromFileName);
            int[] buyies = getBuyiesFromFile(fromFileName);
            int suppliesSum = 0;
            int buyiesSum = 0;
            for (int supply: supplies) {
                suppliesSum += supply;
            }
            for (int buy: buyies) {
                buyiesSum += buy;
            }
            int result = suppliesSum - buyiesSum;
            bufferedWriter.write(SUPPLY_ACTION + "," + suppliesSum + System.lineSeparator());
            bufferedWriter.write(BUY_ACTION + "," + buyiesSum + System.lineSeparator());
            bufferedWriter.write(RESULT_ACTION + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private int[] getSuppliesFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int supplyCount = 0;
            String data = bufferedReader.readLine();
            while (data != null) {
                String[] dataArray = data.split(",");
                if (dataArray.length != DATA_LENGTH) {
                    throw new RuntimeException("Invalid data format");
                } else if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(SUPPLY_ACTION)) {
                    supplyCount++;
                }
                data = bufferedReader.readLine();
            }
            int[] supplies = new int[supplyCount];
            int supplyQuantCount = 0;
            bufferedReader = new BufferedReader(new FileReader(file));
            data = bufferedReader.readLine();
            while (data != null) {
                String[] dataArray = data.split(",");
                if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(SUPPLY_ACTION)) {
                    supplies[supplyQuantCount] = Integer.parseInt(dataArray[INDEX_AMOUNT]);
                    supplyQuantCount++;
                }
                data = bufferedReader.readLine();
            }
            return supplies;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private int[] getBuyiesFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int buyCount = 0;
            String data = bufferedReader.readLine();
            while (data != null) {
                String[] dataArray = data.split(",");
                if (dataArray.length != DATA_LENGTH) {
                    throw new RuntimeException("Invalid data format");
                }
                if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(BUY_ACTION)) {
                    buyCount++;
                }
                data = bufferedReader.readLine();
            }
            int[] buyies = new int[buyCount];
            int buyQuantCount = 0;
            bufferedReader = new BufferedReader(new FileReader(file));
            data = bufferedReader.readLine();
            while (data != null) {
                String[] dataArray = data.split(",");
                if (dataArray[INDEX_DATA_OPERATION_TYPE].equals(BUY_ACTION)) {
                    buyies[buyQuantCount] = Integer.parseInt(dataArray[INDEX_AMOUNT]);
                    buyQuantCount++;
                }
                data = bufferedReader.readLine();
            }
            return buyies;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }
}
