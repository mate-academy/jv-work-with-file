package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String SEPARATOR = ",";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            String value = bufferedReader.readLine();
            int supplyCount = 0;
            int buyCount = 0;

            while (value != null) {
                String[] currentLine = value.split(SEPARATOR);
                if (currentLine.length == 2) {
                    int count = Integer.parseInt(currentLine[1]);
                    if (currentLine[0].contains(SUPPLY)) {
                        supplyCount += count;
                    } else if (currentLine[0].contains(BUY)) {
                        buyCount += count;
                    }
                }

                value = bufferedReader.readLine();
            }

            int result = supplyCount - buyCount;
            bufferedWriter.write(SUPPLY
                    + ","
                    + String.valueOf(supplyCount)
                    + System.lineSeparator());
            bufferedWriter.write(BUY
                    + ","
                    + String.valueOf(buyCount)
                    + System.lineSeparator());
            bufferedWriter.write(RESULT
                    + ","
                    + String.valueOf(result));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Was unable to find the file: ", e);
        } catch (IOException e) {
            throw new RuntimeException("Encountered an error while processing this file: ", e);
        }
    }
}
