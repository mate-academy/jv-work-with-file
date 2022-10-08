package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = "_";
    private static final int PRICE_INDEX = 1;
    private static final int ACTION_INDEX = 0;
    private static final String CHAR_TO_SPLIT = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = getDataArray(fromFileName);
        String[] updatedData = getUpdateData(dataFromFile);
        writeToFile(toFileName, updatedData);
    }

    private String[] getDataArray(String fileName) {
        File readFile = new File(fileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String value = reader.readLine();

            while (value != null) {
                builder.append(value)
                        .append(LINE_SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }

        return builder.toString().split(LINE_SEPARATOR);
    }

    private String[] getUpdateData(String[] dataArray) {
        int supply = 0;
        int buy = 0;

        for (String datum : dataArray) {
            String[] arr = datum.split(CHAR_TO_SPLIT);

            if (arr[ACTION_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(arr[PRICE_INDEX]);
            } else {
                buy += Integer.parseInt(arr[PRICE_INDEX]);
            }
        }

        int result = supply - buy;
        String suppleInfo = SUPPLY + "," + supply;
        String buyInfo = BUY + "," + buy;
        String resultInfo = RESULT + "," + result;
        return new String[]{suppleInfo, buyInfo, resultInfo};
    }

    private void writeToFile(String fileName, String[] dataToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String writeDatum : dataToWrite) {
                writer.write(writeDatum);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}
