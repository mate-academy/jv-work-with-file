package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buyCount = 0;
        int supplyCount = 0;
        int result = 0;

        File fileReader = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReader))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] text = line.split(",");
                if (text.length == 2) {
                    String word = text[0];
                    int count = Integer.parseInt(text[1]);
                    if (word.equals(BUY)) {
                        buyCount += count;
                    }
                    if (word.equals(SUPPLY)) {
                        supplyCount +=count;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read a file", e);
        }

        result = supplyCount - buyCount;
        File fileWriter = new File(toFileName);
        try {
            fileWriter.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create a file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWriter, true))) {

        }
    }
}
