package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        writeData(data, toFileName);
    }

    private String readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return builder.toString();
    }

    private void writeData(String data, String toFileName) {
        String[] split = data.split(" ");
        int buyCount = 0;
        int supplyCount = 0;
        for (int i = 0; i < split.length; i++) {
            String[] splitByComma = split[i].split(COMMA);
            if (splitByComma[0].equals(BUY)) {
                buyCount += Integer.parseInt(splitByComma[1]);
            } else {
                supplyCount += Integer.parseInt(splitByComma[1]);
            }
        }
        String result = prepareData(buyCount, supplyCount);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't record data to file " + toFileName, e);
        }
    }

    private String prepareData(int buyCount, int supplyCount) {
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(SUPPLY).append(COMMA).append(supplyCount)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplyCount - buyCount)
                .append(System.lineSeparator());
        return dataBuilder.toString();
    }
}
