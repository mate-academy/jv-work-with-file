package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int INDEX_OF_OPERATION = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = readFromFile(fromFileName);
        String statistic = getStatistic(inputData);
        writeToFile(toFileName, statistic);
    }

    private String getStatistic(String inputDate) {
        int suppleCounter = 0;
        int buyCounter = 0;
        String[] data = inputDate.split(" ");
        for (String line : data) {
            String[] splittedLine = line.split(",");
            if (splittedLine[INDEX_OF_OPERATION].equals("buy")) {
                buyCounter += Integer.parseInt(splittedLine[INDEX_OF_AMOUNT]);
            } else {
                suppleCounter += Integer.parseInt(splittedLine[INDEX_OF_AMOUNT]);
            }
        }
        int result = suppleCounter - buyCounter;
        StringBuilder statisticBuilder = new StringBuilder();
        return statisticBuilder.append("supply").append(",").append(suppleCounter)
                .append(System.lineSeparator()).append("buy")
                .append(",").append(buyCounter).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append(" ");
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read this file:" + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String statistic) {
        File file = new File(toFileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(statistic);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can not write this file: " + toFileName, e);
        }
    }
}
