package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_VALUE = "supply";
    private static final String BUY_VALUE = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] splitFile = read(fromFileName).split(System.lineSeparator());
        int supplyCount = 0;
        int buyCount = 0;
        int result;

        for (String eachElement : splitFile) {
            String[] toCountElement = eachElement.split(SEPARATOR);
            String type = toCountElement[INDEX_ZERO];
            int count = Integer.parseInt((toCountElement[INDEX_ONE]));
            if (type.equals(SUPPLY_VALUE)) {
                supplyCount += count;
            }
            if (type.equals(BUY_VALUE)) {
                buyCount += count;
            }
        }
        result = supplyCount - buyCount;
        String finalDataFile = dataCreator(supplyCount, buyCount, result);
        write(toFileName, finalDataFile);
    }

    private String dataCreator(int supplyCount, int buyCount, int result) {
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY_VALUE).append(SEPARATOR)
                .append(supplyCount).append(System.lineSeparator())
                .append(BUY_VALUE).append(SEPARATOR).append(buyCount)
                .append(System.lineSeparator()).append(RESULT)
                .append(SEPARATOR).append(result).append(System.lineSeparator()).toString();
    }

    private String read(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                builder.append(value).append(System.lineSeparator());
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read file" + fromFileName, e);
        }
    }

    private void write(String toFileName, String dataResult) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileName, false))) {
            bufferedWriter.write(dataResult);
        } catch (IOException e) {
            throw new RuntimeException("Can not write file" + toFileName, e);
        }
    }
}
