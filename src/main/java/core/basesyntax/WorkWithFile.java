package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION_TYPE_VALUE = 0;
    private static final int INDEX_OF_AMOUNT_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String result = generateResultStatistic(readDataFromFile(fromFileName));
        File file = new File(toFileName);
        writeDataToFile(file, result);
    }

    private String readDataFromFile(String fromFileName) {
        try {
            return Files.readString(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file with name " + fromFileName, e);
        }
    }

    private String generateResultStatistic(String data) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] splittedData = line.split(",");
            sumOfSupply += splittedData[INDEX_OF_OPERATION_TYPE_VALUE].equals("supply")
                    ? Integer.parseInt(splittedData[INDEX_OF_AMOUNT_VALUE]) : 0;
            sumOfBuy += splittedData[INDEX_OF_OPERATION_TYPE_VALUE].equals("buy")
                    ? Integer.parseInt(splittedData[INDEX_OF_AMOUNT_VALUE]) : 0;
        }
        return String.format("supply,%s%sbuy,%s%sresult,%s",
                sumOfSupply, System.lineSeparator(),
                sumOfBuy, System.lineSeparator(),
                (sumOfSupply - sumOfBuy));
    }

    private void writeDataToFile(File file, String result) {
        try {
            Files.writeString(file.toPath(), result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file with name "
                    + file.getName(), e);
        }
    }
}
