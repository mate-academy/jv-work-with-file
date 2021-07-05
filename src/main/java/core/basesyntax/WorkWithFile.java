package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int INDEX_OF_OPERATION = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = makeReport(getDataFromFile(fromFileName));
        writeToFile(toFileName, data);
    }

    private int[] getDataFromFile(String fromFileName) {
        int[] supplyAndBuy = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(COMA);
                if (data[INDEX_OF_OPERATION].equals(BUY)) {
                    supplyAndBuy[1] += Integer.parseInt(data[INDEX_OF_AMOUNT]);
                }
                if (data[INDEX_OF_OPERATION].equals(SUPPLY)) {
                    supplyAndBuy[0] += Integer.parseInt(data[INDEX_OF_AMOUNT]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read a file" + fromFileName, e);
        }
        return supplyAndBuy;
    }

    private String makeReport(int[] supplyAndBuy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMA).append(supplyAndBuy[0])
                .append(System.lineSeparator())
                .append(BUY).append(COMA)
                .append(supplyAndBuy[1])
                .append(System.lineSeparator())
                .append(RESULT).append(COMA)
                .append(supplyAndBuy[0] - supplyAndBuy[1]);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file" + toFileName, e);
        }
    }
}
