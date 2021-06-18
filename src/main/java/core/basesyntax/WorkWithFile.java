package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_PRODUCT_COUNT = 1;
    private static final String WORD_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(readFromFile(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file ", e);
        }
    }

    private String readFromFile(String fileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] splitArray = line.split(WORD_SEPARATOR);
                if (splitArray[INDEX_OF_OPERATION].equals("supply")) {
                    supply += Integer.parseInt(splitArray[INDEX_OF_PRODUCT_COUNT]);
                } else {
                    buy += Integer.parseInt(splitArray[INDEX_OF_PRODUCT_COUNT]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file ", e);
        }
        return new StringBuilder().append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator()).toString();
    }
}
