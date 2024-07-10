package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INNER_ARRAY_SIZE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, calculateReport(readFromFile(fromFileName)));
    }

    private String[][] readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int i = 0;
            int arraySize = createArraySize(fileName);
            String line;
            String[][] sourceArray = new String[arraySize][INNER_ARRAY_SIZE];
            while ((line = bufferedReader.readLine()) != null) {
                sourceArray[i] = line.split(COMMA);
                i++;
            }
            return sourceArray;
        } catch (IOException e) {
            throw new RuntimeException("Can't read info from file", e);
        }
    }

    private int[] calculateReport(String[][] originalSourceArray) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String[] sourceElement : originalSourceArray) {
            if (sourceElement[0].equals("supply")) {
                totalSupply += Integer.parseInt(sourceElement[1]);
            }
            if (sourceElement[0].equals("buy")) {
                totalBuy += Integer.parseInt(sourceElement[1]);
            }
        }
        int[] statisticArray = {totalSupply, totalBuy, totalSupply - totalBuy};
        return statisticArray;
    }

    private void writeToFile(String reportName, int[] statistic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportName))) {
            StringBuilder text = new StringBuilder();
            text.append("supply,").append(statistic[0]).append(System.lineSeparator())
                    .append("buy,").append(statistic[1]).append(System.lineSeparator())
                    .append("result,").append(statistic[2]);
            bufferedWriter.write(text.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write info in file", e);
        }
    }

    private int createArraySize(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int arraySize = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                arraySize++;
            }
            return arraySize;
        } catch (IOException e) {
            throw new RuntimeException("Can't read info from file", e);
        }
    }
}
