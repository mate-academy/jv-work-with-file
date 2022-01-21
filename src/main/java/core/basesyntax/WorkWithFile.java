package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_SUPPLY = 0;
    private static final int INDEX_OF_BUY = 1;
    private static final int INDEX_OF_RESULT = 2;
    private static final int INDEX_OF_SUPPLY_OR_BUY = 0;
    private static final int INDEX_OF_QUANTITY = 1;
    private static final int REQUIRED_CAPACITY = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuyAndResult = getSupplyAndBuyAndResult(fromFileName);
        createReport(supplyAndBuyAndResult, toFileName);
    }

    private int[] getSupplyAndBuyAndResult(String fromFileName) {
        File file = new File(fromFileName);
        int[] supplyAndBuyAndResult = new int[REQUIRED_CAPACITY];
        String[] split;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lineOfFile = reader.readLine();

            while (lineOfFile != null) {
                split = lineOfFile.split(",");
                if (split[INDEX_OF_SUPPLY_OR_BUY].equals("supply")) {
                    supplyAndBuyAndResult[INDEX_OF_SUPPLY]
                            += Integer.parseInt(split[INDEX_OF_QUANTITY]);
                } else if (split[INDEX_OF_SUPPLY_OR_BUY].equals("buy")) {
                    supplyAndBuyAndResult[INDEX_OF_BUY]
                            += Integer.parseInt(split[INDEX_OF_QUANTITY]);
                }
                lineOfFile = reader.readLine();
            }
            reader.close();
            supplyAndBuyAndResult[INDEX_OF_RESULT] = supplyAndBuyAndResult[INDEX_OF_SUPPLY]
                    - supplyAndBuyAndResult[INDEX_OF_BUY];
        } catch (IOException e) {
            throw new RuntimeException("File " + file.getName() + " does not exist!", e);
        }
        return supplyAndBuyAndResult;
    }

    private void createReport(int[] supplyAndBuyAndResult, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < supplyAndBuyAndResult.length; i++) {
                switch (i) {
                    case INDEX_OF_SUPPLY:
                        writer.write("supply,");
                        writer.write(String.valueOf(supplyAndBuyAndResult[i]));
                        writer.write(System.lineSeparator());
                        break;
                    case INDEX_OF_BUY:
                        writer.write("buy,");
                        writer.write(String.valueOf(supplyAndBuyAndResult[i]));
                        writer.write(System.lineSeparator());
                        break;
                    default:
                        writer.write("result,");
                        writer.write(String.valueOf(supplyAndBuyAndResult[i]));
                        writer.write(System.lineSeparator());
                        break;
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("File " + file.getName() + " does not exist!", e);
        }
    }
}
