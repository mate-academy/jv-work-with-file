package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_NUMERIC = 1;

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
            String reader;
            while ((reader = bufferedReader.readLine()) != null) {
                if (reader.contains("supply")) {
                    String [] inheritArray = reader.split(",");
                    supply += Integer.parseInt(inheritArray[INDEX_NUMERIC]);
                }
                if (reader.contains("buy")) {
                    String [] inheritArray = reader.split(",");
                    buy += Integer.parseInt(inheritArray[INDEX_NUMERIC]);
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
