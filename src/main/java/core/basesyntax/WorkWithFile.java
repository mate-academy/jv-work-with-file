package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ROOF_OF_NAMES = 2;
    private static final int INDEX_OF_VALUES = 1;
    private static final int INDEX_OF_SUPPLY_VALUE = 7;
    private static final int INDEX_OF_BUY_VALUE = 4;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder statisticBuilder = new StringBuilder();
        String textFromFile = readFromFile(fromFileName);
        String[] dataArray = textFromFile.split(System.lineSeparator());
        int totalValue;
        for (int i = 0; i < ROOF_OF_NAMES; i++) {
            if (i == 0) {
                statisticBuilder.append("supply,");
            } else {
                statisticBuilder.append("buy,");
            }
            totalValue = 0;
            for (int j = 0; j < dataArray.length; j++) {
                String[] contentOfData = dataArray[j].split(",");
                String name = contentOfData[0];
                int valueOfData = Integer.parseInt(contentOfData[INDEX_OF_VALUES]);
                if (i == 0 && name.equals("supply")) {
                    totalValue += valueOfData;
                }
                if (i == 1 && name.equals("buy")) {
                    totalValue += valueOfData;
                }
            }
            statisticBuilder.append(totalValue).append(System.lineSeparator());
        }
        String completeStr = statisticBuilder.toString();
        String[] completeData = completeStr.split(System.lineSeparator());
        String valueOfSupply = completeData[0].substring(INDEX_OF_SUPPLY_VALUE);
        String valueOfBuy = completeData[1].substring(INDEX_OF_BUY_VALUE);
        try {
            int resultValue = Integer.parseInt(valueOfSupply) - Integer.parseInt(valueOfBuy);
            completeStr += "result," + resultValue;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Incorrect integer format");
        }
        saveToFile(toFileName, completeStr);
    }

    public String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                builder.append(str).append(System.lineSeparator());
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file - " + fileName);
        }
        return builder.toString();
    }

    public void saveToFile(String toFile, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't save data to file - " + toFile);
        }
    }
}
