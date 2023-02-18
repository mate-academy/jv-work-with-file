package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = ",";
    private static final int INDEX_NAME = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int supplyCount = 0;
        int buyCount = 0;
        int result;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lineArray = line.split(SEPARATOR);
                if (lineArray[INDEX_NAME].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(lineArray[INDEX_AMOUNT]);
                } else if (lineArray[INDEX_NAME].equals(BUY)) {
                    buyCount += Integer.parseInt(lineArray[INDEX_AMOUNT]);
                } 
                if (lineArray.length != 2) {
                    throw new RuntimeException("Invalid data in line: " + line);
                }
                line = bufferedReader.readLine();
            }
            result = supplyCount - buyCount;

            stringBuilder.append("supply,").append(supplyCount).append(System.lineSeparator())
                    .append("buy,").append(buyCount).append(System.lineSeparator())
                    .append("result,").append(result);

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
                bufferedWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read to file", e);
        }
    }
}
