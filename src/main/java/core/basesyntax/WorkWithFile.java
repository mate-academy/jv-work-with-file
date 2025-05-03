package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SEPARATOR = ",";
    private static final String SPACE_SEPARATOR = " ";
    private static final int COLUMN_NAME_INDEX = 0;
    private static final int COLUMN_VALUE_INDEX = 1;
    private static final int INITIAL_SUPPLY_COUNT = 0;
    private static final int INITIAL_BUY_COUNT = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToNewFile(toFileName, getReport(readFromFile(fromFileName)));
    }

    public String getReport(String dataFromFile) {
        String[] data = dataFromFile.split(SPACE_SEPARATOR);
        int supplyCount = INITIAL_SUPPLY_COUNT;
        int buyCount = INITIAL_BUY_COUNT;
        for (String value : data) {
            String[] values = value.split(COMMA_SEPARATOR);
            if (values[COLUMN_NAME_INDEX].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(values[COLUMN_VALUE_INDEX]);
            } else if (values[COLUMN_NAME_INDEX].equals(BUY)) {
                buyCount += Integer.parseInt(values[COLUMN_VALUE_INDEX]);
            }
        }
        return SUPPLY + COMMA_SEPARATOR + supplyCount + System.lineSeparator()
                + BUY + COMMA_SEPARATOR + buyCount + System.lineSeparator()
                + RESULT + COMMA_SEPARATOR + (supplyCount - buyCount);
    }

    public void writeToNewFile(String newFileName, String data) {
        File file = new File(newFileName);
        try {
            Files.write(file.toPath(), data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file " + file, e);
        }
    }

    public String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(SPACE_SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        }
        return builder.toString();
    }
}
