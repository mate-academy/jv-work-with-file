package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        String[] dataFromFileArray = dataFromFile.split(" ");
        int supplyCount = 0;
        int buyCount = 0;
        int resultCount = 0;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < dataFromFileArray.length; i++) {
            String[] currentDataArr = dataFromFileArray[i].split(",");
            String nameOfOperation = currentDataArr[OPERATION_INDEX];
            int currentCount = Integer.parseInt(currentDataArr[COUNT_INDEX]);

            if (nameOfOperation.equals(SUPPLY)) {
                supplyCount += currentCount;
            } else {
                buyCount += currentCount;
            }
        }

        resultCount = supplyCount - buyCount;

        stringBuilder.append(SUPPLY).append(",").append(supplyCount).append(System.lineSeparator())
                     .append(BUY).append(",").append(buyCount).append(System.lineSeparator())
                     .append(RESULT).append(",").append(resultCount).append(System.lineSeparator());

        return stringBuilder.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
