package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_BY = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private String line = "";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fromFileName));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(SPLIT_BY);
                if (data[0].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(data[1]);
                } else if (data[0].equals(BUY)) {
                    buyCount += Integer.parseInt(data[1]);
                }
            }
            br.close();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(SPLIT_BY).append(supplyCount)
                    .append(System.lineSeparator());
            stringBuilder.append(BUY).append(SPLIT_BY).append(buyCount)
                    .append(System.lineSeparator());
            stringBuilder.append(RESULT).append(SPLIT_BY).append(supplyCount - buyCount);
            FileWriter fileWriter = new FileWriter(new File(toFileName));
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }
}
