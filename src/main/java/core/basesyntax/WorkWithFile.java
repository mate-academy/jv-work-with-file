package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dateFromFile = readFromFile(fromFileName);
        String[] strings = dateFromFile.split(System.lineSeparator());
        String dataReadyToWrite = sortDataFromFile(strings);
        writeToFile(toFileName, dataReadyToWrite);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String sortDataFromFile(String[] data) {
        String[] dataUnit;
        String supply = "supply";
        String buy = "buy";
        String result = "result";
        StringBuilder stringBuilder = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;
        for (int i = 0; i < data.length; i++) {
            dataUnit = data[i].split(",");
            if (dataUnit[0].equals(supply)) {
                supplyAmount += Integer.parseInt(dataUnit[1]);
            }
            if (dataUnit[0].equals(buy)) {
                buyAmount += Integer.parseInt(dataUnit[1]);
            }
        }
        stringBuilder.append(supply).append(',').append(supplyAmount).append(System.lineSeparator())
                .append(buy).append(",").append(buyAmount).append(System.lineSeparator())
                .append(result).append(",").append(supplyAmount - buyAmount);
        return stringBuilder.toString();
    }
}
