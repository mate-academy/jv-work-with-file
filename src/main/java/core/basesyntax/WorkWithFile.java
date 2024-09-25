package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_CONSTANT = "buy";
    private static final String COMMA = ",";
    private static final String RESULT_CONSTANT = "result";
    private static final String SUPPLY_CONSTANT = "supply";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String newFile) {
        int[] stats = readStatistic(fromFileName);
        String result = countStatistic(fromFileName, stats);
        writeStatistic(result, newFile);
    }

    private int[] readStatistic(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(COMMA);
                if (split[OPERATION_INDEX].equals(SUPPLY_CONSTANT)) {
                    supply += Integer.parseInt(split[AMOUNT_INDEX]);
                } else if (split[OPERATION_INDEX].equals(BUY_CONSTANT)) {
                    buy += Integer.parseInt(split[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return new int[]{supply, buy};
    }

    private String countStatistic(String fromFileName, int[] stats) {
        int supply = stats[OPERATION_INDEX];
        int buy = stats[AMOUNT_INDEX];
        int countedResult = supply - buy;

        StringBuilder result = new StringBuilder();
        return result.append(SUPPLY_CONSTANT).append(COMMA).append(supply)
                .append(System.lineSeparator()).append(BUY_CONSTANT).append(COMMA)
                .append(buy).append(System.lineSeparator()).append(RESULT_CONSTANT)
                .append(COMMA).append(countedResult).toString();
    }

    private void writeStatistic(String result, String newFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
