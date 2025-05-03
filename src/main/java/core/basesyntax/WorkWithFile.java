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
        String dataFromFile = getDataFromFile(fromFileName);
        String statisticData = getStatisticData(dataFromFile);
        writeDataToFile(toFileName, statisticData);
    }

    private void writeDataToFile(String fileName, String data) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file!", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file!", e);
        }
    }

    private String getStatisticData(String data) {
        String[] firstArray = data.split(System.lineSeparator());
        String[][] secondArray = new String[firstArray.length][];
        for (int i = 0; i < secondArray.length; i++) {
            secondArray[i] = firstArray[i].split(",");
        }
        int sumSupply = 0;
        int sumBuy = 0;
        for (String[] strings : secondArray) {
            sumSupply += (strings[0].equals("supply")) ? Integer.parseInt(strings[1]) : 0;
            sumBuy += (strings[0].equals("buy")) ? Integer.parseInt(strings[1]) : 0;
        }
        int result = sumSupply - sumBuy;
        return convertToString(sumSupply, sumBuy, result);
    }

    private String convertToString(int supply, int buy, int result) {
        return new StringBuilder().append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator()).append("result,")
                .append(result).toString();
    }

    private String getDataFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file!", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't get data!", e);
        }
        return stringBuilder.toString();
    }
}
