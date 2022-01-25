package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int INDEX_FOR_OPERATION_TYPE = 0;
    private static final int INDEX_FOR_AMOUNT = 1;
    private static final int INDEX_FOR_SUPPLY_AMOUNT = 0;
    private static final int INDEX_FOR_BUY_AMOUNT = 1;
    private static final String SEPARATOR_FOR_DATA = ",";
    private static final String WORD_BUY = "buy";
    private static final String WORD_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        int[] arraySupplyBuyAmount = countData(data);
        byte[] result = createReport(arraySupplyBuyAmount);
        writeToFile(result, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return builder.toString();
    }

    private int[] countData(String data) {
        String[] arrayData = data.split(System.lineSeparator());
        int supplyAmout = 0;
        int buyAmout = 0;
        for (int i = 0; i < arrayData.length; i++) {
            String[] temp = arrayData[i].split(SEPARATOR_FOR_DATA);
            if (temp[INDEX_FOR_OPERATION_TYPE].equals(WORD_SUPPLY)) {
                supplyAmout += Integer.parseInt(temp[INDEX_FOR_AMOUNT]);
            }
            if (temp[INDEX_FOR_OPERATION_TYPE].equals(WORD_BUY)) {
                buyAmout += Integer.parseInt(temp[INDEX_FOR_AMOUNT]);
            }
        }
        return new int[]{supplyAmout, buyAmout};
    }

    private byte[] createReport(int[] arraySupplyBuyAmount) {
        StringBuilder builderResult = new StringBuilder();
        builderResult.append(WORD_SUPPLY)
                .append(SEPARATOR_FOR_DATA)
                .append(arraySupplyBuyAmount[INDEX_FOR_SUPPLY_AMOUNT])
                .append(System.lineSeparator())
                .append(WORD_BUY)
                .append(SEPARATOR_FOR_DATA)
                .append(arraySupplyBuyAmount[INDEX_FOR_BUY_AMOUNT])
                .append(System.lineSeparator())
                .append("result")
                .append(SEPARATOR_FOR_DATA)
                .append(arraySupplyBuyAmount[INDEX_FOR_SUPPLY_AMOUNT]
                        - arraySupplyBuyAmount[INDEX_FOR_BUY_AMOUNT]);
        return builderResult.toString().getBytes();
    }

    private void writeToFile(byte[] result, String toFileName) {
        try {
            Files.write(Path.of(toFileName), result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }
}
