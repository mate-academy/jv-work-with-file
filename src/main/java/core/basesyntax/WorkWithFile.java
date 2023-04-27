package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int LIMIT_OF_LOOP = -1;
    private static final String SPECIFIED_CHARACTERS = "[\n\r]";
    private static final int ARRAY_INDEX = 1;

    private String readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int value = reader.read();
            while (value != LIMIT_OF_LOOP) {
                fileContent.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent.toString();
    }

    private int[] calculateStatistic(String dataFromFile) {
        int buyCounter = 0;
        int supplyCounter = 0;
        int result;
        String data = dataFromFile.replaceAll(SPECIFIED_CHARACTERS, ",");
        String[] dataArray = data.split(",");
        for (int i = 0; i < dataArray.length + LIMIT_OF_LOOP; i++) {
            if (dataArray[i].equals("buy")) {
                buyCounter += Integer.parseInt(dataArray[i + ARRAY_INDEX]);
            } else if (dataArray[i].equals("supply")) {
                supplyCounter += Integer.parseInt(dataArray[i + ARRAY_INDEX]);
            }
        }
        result = supplyCounter - buyCounter;
        return new int[]{supplyCounter, buyCounter, result};
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statisticArray = calculateStatistic(readFile(fromFileName));
        File file = new File(toFileName);
        StringBuilder statistic = new StringBuilder();
        statistic.append("supply,").append(statisticArray[0]).append("\r")
                .append("\nbuy,").append(statisticArray[1]).append("\r")
                .append("\nresult,").append(statisticArray[2]);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            writer.write(statistic.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
