package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = getDataFromFile(fromFileName);
        String[] dataBefore = dataFromFile.split(System.lineSeparator());
        int buyCount = 0;
        int supplyCount = 0;
        for (String s : dataBefore) {
            String[] dataAfter = s.split(",");
            if (dataAfter[0].equals(BUY)) {
                buyCount += Integer.parseInt(dataAfter[1]);
            }
            if (dataAfter[0].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(dataAfter[1]);
            }
        }
        int result = supplyCount - buyCount;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                .append(",").append(supplyCount).append(System.lineSeparator())
                .append(BUY).append(",").append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(",").append(result);

        writeDataToFile(toFileName, stringBuilder.toString());
    }

    private String getDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private void writeDataToFile(String toFileName, String dataReport) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(dataReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
