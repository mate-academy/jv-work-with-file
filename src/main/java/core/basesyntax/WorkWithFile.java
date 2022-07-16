package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        final String supply = "supply";
        final String delimiter = ",";
        int totalSupply = 0;
        int totalBuy = 0;
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] tempArray = value.split(delimiter);
                if (tempArray[0].equals(supply)) {
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

    private void writeStatistic(String toFileName, int totalSupply, int totalBuy, int totalResult) {
        final String supply = "supply";
        final String buy = "buy";
        final String result = "result";
        final String delimiter = ",";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(supply).append(delimiter).append(totalSupply)
                .append(System.lineSeparator())
                .append(buy).append(delimiter).append(totalBuy).append(System.lineSeparator())
                .append(result).append(delimiter).append(totalResult);
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file", e);
        }
    }
}
