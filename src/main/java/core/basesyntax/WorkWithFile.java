package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String separatorItem = ",";
    private final String firstItem = "supply";
    private final String secondItem = "buy";
    private int totalSupply = 0;
    private int totalBuy = 0;
    private int result = 0;
    private String[] dataArray;

    public void getStatistic(String fromFileName, String toFileName) {
        readFileMethod(fromFileName);
        writeDataToFileMethod(toFileName);
    }

    private void readFileMethod(String fileName) {
        String line;
        try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
            while ((line = read.readLine()) != null) {
                dataArray = line.split(separatorItem);
                countData(dataArray);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error ",e);
        }
    }

    private void countData(String[] arr) {
        for (String data : arr) {
            int value = Integer.parseInt(dataArray[1]);
            if (data.equals(firstItem)) {
                totalSupply += value;
            }
            if (data.equals(secondItem)) {
                totalBuy += value;
            }
        }
        result = totalSupply - totalBuy;
    }

    private void writeDataToFileMethod(String fileName) {
        try (BufferedWriter writeFile = new BufferedWriter(new FileWriter(fileName, true))) {
            String build = firstItem + separatorItem + totalSupply + System.lineSeparator()
                    + secondItem + separatorItem + totalBuy + System.lineSeparator()
                    + "result" + separatorItem + result;
            writeFile.write(build);
        } catch (IOException e) {
            throw new RuntimeException(" Error ", e);
        }
    }
}
