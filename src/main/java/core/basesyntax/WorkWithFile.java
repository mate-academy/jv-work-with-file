package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private final String SPLIT_BY = ",";
    private final String SUPPLY = "supply";
    private final String BUY = "buy";
    private final String RESULT = "result";
    private String line = "";
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fromFileName));
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(SPLIT_BY);
                if (data[0].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(data[1]);
                } else if (data[0].equals(BUY)) {
                    buyCount += Integer.parseInt(data[1]);
                }
            }
            br.close();
            File csvFile = new File(toFileName);
            FileWriter fileWriter = new FileWriter(csvFile);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(SPLIT_BY).append(supplyCount).append("\n");
            stringBuilder.append(BUY).append(SPLIT_BY).append(buyCount).append("\n");
            stringBuilder.append(RESULT).append(SPLIT_BY).append(supplyCount - buyCount);
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

    }
}
