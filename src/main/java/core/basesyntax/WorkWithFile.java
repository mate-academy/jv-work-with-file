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
        String data = getDataFromFile(fromFileName);
        writeToFile(toFileName, data);
    }

    private String getDataFromFile(String fromFileName) {
        int countOfSupply = 0;
        int countOfBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lines = line.split(COMA);
                if (lines[INDEX_OF_OPERATION].equals(BUY)) {
                    countOfBuy += Integer.parseInt(lines[INDEX_OF_AMOUNT]);
                }
                if (lines[INDEX_OF_OPERATION].equals(SUPPLY)) {
                    countOfSupply += Integer.parseInt(lines[INDEX_OF_AMOUNT]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read a file" + fromFileName, e);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMA).append(countOfSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMA)
                .append(countOfBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMA)
                .append(countOfSupply - countOfBuy);
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
