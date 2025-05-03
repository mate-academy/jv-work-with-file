package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CHECK_SUPPLY = "supply";
    private static final String SEPARATOR = " ";
    private static final String CSV_SEPARATOR = ",";
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String[] calculatedStatistic = calculateStatistic(dataFromFile);
        writeFile(calculatedStatistic, toFileName);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SEPARATOR);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file: ", e);
        }
        return stringBuilder.toString().split(SEPARATOR);
    }

    private void writeFile(String[] calculatedStatistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String data : calculatedStatistic) {
                bufferedWriter.write(data);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing to the file: ", e);
        }

    }

    private String[] calculateStatistic(String[] dataFromFile) {
        int supplySum = 0;
        int buySum = 0;
        int ammount;
        for (String line : dataFromFile) {
            String[] lineSeparation = line.split(CSV_SEPARATOR);
            ammount = Integer.parseInt(lineSeparation[AMOUNT_INDEX]);
            if (lineSeparation[OPERATION_INDEX].equals(CHECK_SUPPLY)) {
                supplySum += ammount;
            } else {
                buySum += ammount;
            }
        }
        int result = supplySum - buySum;
        return new String[]{"supply," + supplySum, "buy," + buySum, "result," + result};
    }
}
