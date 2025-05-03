package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String SEPARATOR = " ";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readDataFromFile(fromFileName);
        String[] calcData = calculatedStatistic(data);
        writeDataToFile(calcData, toFileName);
    }

    private String[] readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SEPARATOR);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return stringBuilder.toString().split(SEPARATOR);
    }

    private String[] calculatedStatistic(String[] calcStatistic) {
        int supplySum = 0;
        int buySum = 0;
        for (String line : calcStatistic) {
            String[] lineSeparator = line.split(CSV_SEPARATOR);
            int quantity = Integer.parseInt(lineSeparator[AMOUNT_INDEX]);
            if (lineSeparator[OPERATION_INDEX].equals("supply")) {
                supplySum += quantity;
            } else {
                buySum += quantity;
            }
        }
        int resulSum = supplySum - buySum;
        return new String[]{"supply," + supplySum, "buy," + buySum, "result," + resulSum};
    }

    private void writeDataToFile(String[] calcStatistic, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String data : calcStatistic) {
            stringBuilder.append(data).append(System.lineSeparator());
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
