package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "Supply";
    private static final String BUY = "Buy";
    private static final String RESULT = "Result";
    private static final int SEARCH_BY_INDEX = 0;
    private static final int COUNT_BY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            readingFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void readingFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String value = bufferedReader.readLine();
        while (value != null) {
        }
        value = bufferedReader.readLine();
        preparingResult(value);
    }

    private void preparingResult(String value) {
        int supplyCount = 0;
        int buyCount = 0;
        String[] listFromFile = value.split(",");
        for (int i = 0; i < listFromFile.length; i++) {
            if (listFromFile[SEARCH_BY_INDEX].equals(SUPPLY)) {
                supplyCount = supplyCount + Integer.parseInt(String.valueOf(COUNT_BY_INDEX));
            }
            if (listFromFile[SEARCH_BY_INDEX].equals(BUY)) {
                buyCount = buyCount + Integer.parseInt(String.valueOf(COUNT_BY_INDEX));
            }
        }
        resultFile(supplyCount, buyCount);
    }
    private void resultFile(int supplyCount, int buyCount) {
        StringBuilder builder = new StringBuilder();
        int result = supplyCount - buyCount;
        builder.append(SUPPLY)
                .append(",").append(supplyCount)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buyCount)
                .append(System.lineSeparator())
                .append(RESULT).append(",")
                .append(result);
    }
}
