package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    static final String SUPPLY = "supply";
    static final String BUY = "buy";
    static final String RESULT = "result";
    static final String SEPARATOR = ",";
    static final String SPLITTER = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToNewFile(toFileName, getReport(readFromFile(fromFileName)));
    }

    public String getReport(String dataFromFile) {
        String[] data = dataFromFile.split(SPLITTER);
        int supplyCount = 0;
        int buyCount = 0;
        for (String value : data) {
            int splitIndex = value.indexOf(SEPARATOR);
            int count = Integer.parseInt(value.substring(splitIndex + 1));
            if (value.substring(0, splitIndex).equals(SUPPLY)) {
                supplyCount += count;
            } else if (value.substring(0, splitIndex).equals(BUY)) {
                buyCount += count;
            }
        }
        return SUPPLY + SEPARATOR + supplyCount + System.lineSeparator()
                + BUY + SEPARATOR + buyCount + System.lineSeparator()
                + RESULT + SEPARATOR + (supplyCount - buyCount);
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
                builder.append(value).append(SPLITTER);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        }
        return builder.toString();
    }
}
