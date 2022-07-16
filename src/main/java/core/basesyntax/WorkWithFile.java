package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] tempArray = value.split(DELIMITER);
                if (tempArray[0].equals(SUPPLY)) {
                    totalSupply += Integer.parseInt(tempArray[1]);
                } else {
                    totalBuy += Integer.parseInt(tempArray[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found ", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        writeStatistic(toFileName, totalSupply, totalBuy, totalSupply - totalBuy);
    }

    private void writeStatistic(String toFileName, int totalSupply, int totalBuy, int result) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(DELIMITER).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(totalBuy).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(result);
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file", e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close a file", e);
            }
        }
    }
}
