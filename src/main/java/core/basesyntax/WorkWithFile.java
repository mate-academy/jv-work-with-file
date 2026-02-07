package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final int ACTION_COLUMN_INDEX = 0;
    public static final int COUNT_COLUMN_INDEX = 1;
    public static final int COUNT_COLUMN = 1;
    public static final String NAME_COLUMN_BUY = "buy";
    public static final String NAME_COLUMN_SUPPLY = "supply";
    public static final String NAME_COLUMN_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataLineArray = getLineArray(fromFileName);
        String processedData = getProcessedData(dataLineArray);
        writeProcessedData(processedData, toFileName);
    }

    private String[] getLineArray(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String textLine = bufferedReader.readLine();
            while (textLine != null) {
                stringBuilder.append(textLine).append(System.lineSeparator());
                textLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String getProcessedData(String[] dataLineArray) {
        int buySum = 0;
        int supplySum = 0;
        for (String dataLine : dataLineArray) {
            String[] infoArray = dataLine.split(",");
            if (infoArray.length < COUNT_COLUMN) {
                continue;
            }
            if (infoArray[ACTION_COLUMN_INDEX].equals(NAME_COLUMN_BUY)) {
                buySum += Integer.parseInt(infoArray[COUNT_COLUMN_INDEX]);
            } else if (infoArray[ACTION_COLUMN_INDEX].equals(NAME_COLUMN_SUPPLY)) {
                supplySum += Integer.parseInt(infoArray[COUNT_COLUMN_INDEX]);
            }
        }
        return getProcessedString(buySum, supplySum);
    }

    private String getProcessedString(int buySum, int supplySum) {
        int resultSum = supplySum - buySum;

        return NAME_COLUMN_SUPPLY + "," + supplySum
                + System.lineSeparator()
                + NAME_COLUMN_BUY + "," + buySum
                + System.lineSeparator()
                + NAME_COLUMN_RESULT + "," + resultSum;
    }

    private void writeProcessedData(String processedData, String fileName) {
        try {
            Files.write(Path.of(fileName), processedData.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}

