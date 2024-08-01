package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int START_AMOUNT_VALUE = 0;
    private static final int ARRAY_POSITION_0 = 0;
    private static final int ARRAY_POSITION_1 = 1;
    private static final int ARRAY_SPLIT_POINT_0 = 0;
    private static final int ARRAY_SPLIT_POINT_1 = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] amountValue = getAmountValue(fromFileName);
        int supplyAmount = amountValue[ARRAY_POSITION_0];
        int buyAmount = amountValue[ARRAY_POSITION_1];
        String statistic = createReport(supplyAmount, buyAmount);
        writeFile(statistic, toFileName);
    }

    private int[] getAmountValue(String fromFileName) {
        String text;
        int supplyAmount = START_AMOUNT_VALUE;
        int buyAmount = START_AMOUNT_VALUE;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((text = bufferedReader.readLine()) != null) {
                String[] splitLine = text.split(COMA);
                if (splitLine[ARRAY_SPLIT_POINT_0].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(splitLine[ARRAY_SPLIT_POINT_1]);
                } else if (splitLine[ARRAY_SPLIT_POINT_0].equals(BUY)) {
                    buyAmount += Integer.parseInt(splitLine[ARRAY_SPLIT_POINT_1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
        return new int[]{supplyAmount, buyAmount};
    }

    private String createReport(int supplyAmount, int buyAmount) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMA).append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(supplyAmount - buyAmount);
        return builder.toString();
    }

    private void writeFile(String data, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
