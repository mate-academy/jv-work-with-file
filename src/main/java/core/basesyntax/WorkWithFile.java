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

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            prepareCountResult(file, toFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void prepareCountResult(File file, String toFileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String value = bufferedReader.readLine();
        int supplyCount = 0;
        int buyCount = 0;
        while (value != null) {
            String[] listFromFile = value.split(",");
            for (int i = 0; i < listFromFile.length; i++) {
                if (listFromFile[SEARCH_BY_INDEX].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(listFromFile[1]);
                }
                if (listFromFile[SEARCH_BY_INDEX].equals(BUY)) {
                    buyCount += Integer.parseInt(listFromFile[1]);
                }
            }
            value = bufferedReader.readLine();
        }
        resultFile(toFileName, supplyCount, buyCount);
    }

    private void resultFile(String toFileName, int supplyCount, int buyCount) {
        File file = new File(toFileName);
        int resultCount = supplyCount - buyCount;
    }
}
