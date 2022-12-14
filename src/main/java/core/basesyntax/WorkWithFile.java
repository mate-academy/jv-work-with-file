package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION_TYPE_VALUE = 0;
    private static final int INDEX_OF_AMOUNT_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] result = generateResultStatistic(readDataFromFile(fromFileName));
        File file = new File(toFileName);
        if (file.exists()) {
            file.delete();
        }
        writeDataToFile(file, result);
    }

    private Object[] readDataFromFile(String fromFileName) {
        try {
            return Files.readAllLines(new File(fromFileName).toPath()).toArray();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }
    }

    private String[] generateResultStatistic(Object[] data) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (Object each : data) {
            String[] splittedData = each.toString().split(",");
            sumOfSupply += splittedData[INDEX_OF_OPERATION_TYPE_VALUE].equals("supply")
                    ? Integer.parseInt(splittedData[INDEX_OF_AMOUNT_VALUE]) : 0;
            sumOfBuy += splittedData[INDEX_OF_OPERATION_TYPE_VALUE].equals("buy")
                    ? Integer.parseInt(splittedData[INDEX_OF_AMOUNT_VALUE]) : 0;
        }
        int result = sumOfSupply - sumOfBuy;
        StringBuilder stringBuilder = new StringBuilder("supply,");
        stringBuilder.append(sumOfSupply).append("_")
                .append("buy,").append(sumOfBuy).append("_")
                .append("result,").append(result);
        return stringBuilder.toString().split("_");
    }

    private void writeDataToFile(File file, String[] result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String line : result) {
                bufferedWriter.write(line);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data");
        }
    }
}
