package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SPASE = " ";
    private static final String REGEX = "\\W+";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] splitArray = readLine(fromFileName);
        String calculateSolution = calculateStatistic(splitArray);
        writeToFile(toFileName, calculateSolution);
    }

    private String[] readLine(String filePath) {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitArray;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value).append(SPASE);
            }

            splitArray = stringBuilder.toString().split(REGEX);

            return splitArray;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + filePath, e);
        }
    }

    private String calculateStatistic(String[] splitArray) {
        int sumSupply = 0;
        int sumBuy = 0;

        for (int i = 0; i < splitArray.length; i++) {
            switch (splitArray[i]) {
                case "supply":
                    sumSupply += Integer.parseInt(splitArray[i + 1]);
                    i++;
                    break;
                default:
                    sumBuy += Integer.parseInt(splitArray[i + 1]);
                    i++;
                    break;
            }
        }
        int calculation = sumSupply - sumBuy;

        String s = "supply," + sumSupply + SEPARATOR
                + "buy," + sumBuy + SEPARATOR
                + "result," + calculation;

        return s;
    }

    private void writeToFile(String fileName, String content) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fileName, e);
        }
    }
}
