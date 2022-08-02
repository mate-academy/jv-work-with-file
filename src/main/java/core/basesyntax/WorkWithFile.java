package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String readFromFile = readFromFile(fromFileName);
        String statistic = getStatistic(readFromFile);
        writeToFile(toFileName, statistic);
    }

    private String getStatistic(String data) {
        String[] splitedData = data.split(" ");
        int supplySum = 0;
        int buySum = 0;
        for (String line : splitedData) {
            String[] splitLine = line.split(",");
            if (splitLine[OPERATION_TYPE_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            } else if (splitLine[OPERATION_TYPE_INDEX].equals("buy")) {
                buySum += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
        }
        StringBuilder statisticBuilder = new StringBuilder();
        statisticBuilder.append("supply").append(",").append(supplySum)
                .append(System.lineSeparator())
                .append("buy").append(",").append(buySum)
                .append(System.lineSeparator())
                .append("result").append(",").append(supplySum - buySum);
        return statisticBuilder.toString();
    }

    private String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line)
                        .append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open the file", e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String content) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
