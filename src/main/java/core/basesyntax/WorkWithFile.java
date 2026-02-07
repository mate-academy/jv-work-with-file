package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final int ACTION_COLUMN_INDEX = 0;
    public static final int COUNT_COLUMN_INDEX = 1;
    public static final String NAME_COLUMN_BUY = "buy";
    public static final String NAME_COLUMN_SUPPLY = "supply";
    public static final String NAME_COLUMN_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataLineArray = getLineArray(fromFileName);
        String processedData = getProcessedData(dataLineArray);
        writeProcessedData(processedData, toFileName);
    }

    public String[] getLineArray(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String textLine = bufferedReader.readLine();
            while (textLine != null) {
                stringBuilder.append(textLine).append(System.lineSeparator());
                textLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("file don`t exist or can`t be read", e);
        }

        return stringBuilder.toString().split(System.lineSeparator());
    }

    public String getProcessedData(String[] dataLineArray) {
        StringBuilder stringBuilder = new StringBuilder();
        int buySum = 0;
        int supplySum = 0;

        for (String dataLine : dataLineArray) {
            String[] infoArray = dataLine.split(",");
            String actionName = infoArray[ACTION_COLUMN_INDEX];
            int actionCount = Integer.parseInt(infoArray[COUNT_COLUMN_INDEX]);

            if (actionName.equals(NAME_COLUMN_BUY)) {
                buySum += actionCount;
            }

            if (actionName.equals(NAME_COLUMN_SUPPLY)) {
                supplySum += actionCount;
            }
        }

        int resultSum = supplySum - buySum;

        stringBuilder
                .append(NAME_COLUMN_SUPPLY).append(",").append(supplySum)
                .append(System.lineSeparator())
                .append(NAME_COLUMN_BUY).append(",").append(buySum)
                .append(System.lineSeparator())
                .append(NAME_COLUMN_RESULT).append(",").append(resultSum);

        return stringBuilder.toString();
    }

    public void writeProcessedData(String processedData, String fileName) {
        try {
            Files.write(Path.of(fileName), processedData.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
