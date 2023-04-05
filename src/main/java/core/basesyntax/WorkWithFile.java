package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        data = workWithFileData(data);
        writeToFile(toFileName, data);
    }

    private static String readFromFile(String filename) {
        StringBuilder information = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                information.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        return information.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file", e);
        }
    }

    private String workWithFileData(String data) {
        int sumOfBuy = 0;
        int sumOfSupply = 0;
        String[] splitData;
        StringBuilder result = new StringBuilder();
        String[] dataArray = data.split(System.lineSeparator());
        Map<String, Integer> operationMap = new LinkedHashMap<>();

        for (int i = 0; i < dataArray.length; i++) {
            splitData = dataArray[i].split(",");
            if (splitData[0].equals("supply")) {
                sumOfSupply += Integer.valueOf(splitData[1]);
                operationMap.put(splitData[0], sumOfSupply);
            } else {
                sumOfBuy += Integer.valueOf(splitData[1]);
                operationMap.put(splitData[0], sumOfBuy);
            }
        }

        result.append("supply,").append(operationMap.get("supply")).append(System.lineSeparator())
                .append("buy,").append(operationMap.get("buy")).append(System.lineSeparator())
                .append("result,").append(operationMap.get("supply") - operationMap.get("buy"));

        return result.toString().trim();
    }
}
