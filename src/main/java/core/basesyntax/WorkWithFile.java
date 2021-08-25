package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_AMOUNT = 0;
    private static final int BUY_AMOUNT = 1;
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readFile = readToFile(fromFileName);
        String report = createReport(readFile);
        writeToFile(toFileName, report);
    }

    private String[] readToFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't rear file " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private int[] calculateForReport(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String datum : data) {
            String[] dataArr = datum.split(CSV_SEPARATOR);
            int amount = Integer.parseInt(dataArr[AMOUNT_INDEX]);
            if (dataArr[OPERATION_TYPE].equals(SUPPLY)) {
                supply += amount;
            } else if (dataArr[OPERATION_TYPE].equals(BUY)) {
                buy += amount;
            }
        }
        return new int[]{supply, buy};
    }

    private String createReport(String[] data) {
        int[] amounts = calculateForReport(data);
        int supplyAmount = amounts[SUPPLY_AMOUNT];
        int buyAmount = amounts[BUY_AMOUNT];
        return new StringBuilder()
                .append(SUPPLY).append(CSV_SEPARATOR).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY).append(CSV_SEPARATOR).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT).append(CSV_SEPARATOR).append(supplyAmount - buyAmount)
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
