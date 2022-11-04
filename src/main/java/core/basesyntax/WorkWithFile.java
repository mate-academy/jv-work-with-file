package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY = "Supply";
    private static final String BUY = "Buy";
    private static final String RESULT = "Result";


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
                if (listFromFile[0].equals(SUPPLY)) {
                    supplyCount = supplyCount + Integer.parseInt(listFromFile[1]);
                }
                if (listFromFile[0].equals(BUY)) {
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
