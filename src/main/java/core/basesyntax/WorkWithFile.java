package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_PRODUCT = "supply";
    private static final String BUY_PRODUCT = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATED_VALUE = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataSplitted = read(fromFileName).split(System.lineSeparator());
        int supplyCount = 0;
        int buyCount = 0;
        int result;

        for (String eachData : dataSplitted) {
            String[] toCountData = eachData.split(SEPARATED_VALUE);
            if (toCountData[INDEX_ZERO].equals(SUPPLY_PRODUCT)) {
                supplyCount += Integer.parseInt(toCountData[INDEX_ONE]);
            }
            if (toCountData[INDEX_ZERO].equals(BUY_PRODUCT)) {
                buyCount += Integer.parseInt(toCountData[INDEX_ONE]);
            }
        }
        result = supplyCount - buyCount;
        String finalDataToFile = dataCreator(supplyCount,
                buyCount, result);
        write(toFileName, finalDataToFile);
    }

    private String dataCreator(int supplyCount, int buyCount, int result) {
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY_PRODUCT).append(SEPARATED_VALUE)
                .append(supplyCount).append(System.lineSeparator())
                .append(BUY_PRODUCT).append(SEPARATED_VALUE).append(buyCount)
                .append(System.lineSeparator()).append(RESULT)
                .append(SEPARATED_VALUE).append(result)
                .append(System.lineSeparator()).toString();
    }

    private String read(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private void write(String toFileName, String dataReport) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(dataReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }
}
