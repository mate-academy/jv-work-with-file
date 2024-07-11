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
    private static final int TWO = 2;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        calculateReport(readFromFile(fromFileName), toFileName);
    }

    private String[][] readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int i = ZERO;
            int arraySize = createArraySize(fileName);
            String line;
            String[][] sourceArray = new String[arraySize][TWO];
            while ((line = bufferedReader.readLine()) != null) {
                sourceArray[i] = line.split(COMMA);
                i++;
            }
            return sourceArray;
        } catch (IOException e) {
            throw new RuntimeException("Can't read info from file", e);
        }
    }

    private void calculateReport(String[][] originalSourceArray, String reportName) {
        int totalSupply = ZERO;
        int totalBuy = ZERO;
        for (String[] sourceElement : originalSourceArray) {
            if (sourceElement[ZERO].equals("supply")) {
                totalSupply += Integer.parseInt(sourceElement[ONE]);
            }
            if (sourceElement[ZERO].equals("buy")) {
                totalBuy += Integer.parseInt(sourceElement[ONE]);
            }
        }
        int[] statisticArray = {totalSupply, totalBuy, totalSupply - totalBuy};
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportName))) {
            StringBuilder text = new StringBuilder();
            text.append("supply,").append(statisticArray[ZERO]).append(System.lineSeparator())
                    .append("buy,").append(statisticArray[ONE]).append(System.lineSeparator())
                    .append("result,").append(statisticArray[TWO]);
            bufferedWriter.write(text.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write info in file", e);
        }
    }

    private int createArraySize(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int arraySize = ZERO;
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
