package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    static final String SUPPLY = "supply";
    static final String BUY = "buy";
    static final String SEPARATOR = ",";
    static final String SPLITTER = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] splitData = readFromFile(fromFileName).split(SPLITTER);
        int supplyCount = 0;
        int buyCount = 0;
        for (String data : splitData) {
            int splitIndex = data.indexOf(SEPARATOR);
            int count = Integer.parseInt(data.substring(splitIndex + 1));
            if (data.substring(0, splitIndex).equals(SUPPLY)) {
                supplyCount += count;
            } else if (data.substring(0, splitIndex).equals(BUY)) {
                buyCount += count;
            }
        }

        String data = SUPPLY + SEPARATOR + supplyCount + System.lineSeparator() + BUY + SEPARATOR
                + buyCount + System.lineSeparator() + "result," + (supplyCount - buyCount);
        try {
            Files.write(new File(toFileName).toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
