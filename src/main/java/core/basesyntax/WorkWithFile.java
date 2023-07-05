package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String COLUMN_SEPARATOR = ",";

    private int supply;
    private int buy;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        calculateStatistic(fromFileName);
        saveStatisticToFile(toFileName);
    }

    private void calculateStatistic(String fromFileName) {
        supply = 0;
        buy = 0;
        for (String lineOfData : getDataFromFile(fromFileName)) {
            if (getOperationType(lineOfData).equals("supply")) {
                supply += getOperationAmount(lineOfData);
            } else {
                buy += getOperationAmount(lineOfData);
            }
        }
        result = supply - buy;
    }

    private void saveStatisticToFile(String toFileName) {
        StringBuilder textTorWrite = new StringBuilder("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(result);
        createFile(toFileName);
        writeDataToFile(toFileName, textTorWrite.toString());
    }

    private List<String> getDataFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from file" + fromFileName, e);
        }
    }

    private void writeDataToFile(String toFileName, String text) {
        try {
            Files.write(Path.of(toFileName), text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file" + toFileName, e);
        }
    }

    private void createFile(String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cant create file" + fileName, e);
        }
    }

    private String getOperationType(String lineOfData) {
        return lineOfData.split(COLUMN_SEPARATOR)[OPERATION_TYPE_INDEX];
    }

    private int getOperationAmount(String lineOfData) {
        return Integer.parseInt(lineOfData.split(COLUMN_SEPARATOR)[AMOUNT_INDEX]);
    }
}
