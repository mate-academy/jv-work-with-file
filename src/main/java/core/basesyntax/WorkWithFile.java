package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private int supplyTotal = 0;
    private int buyTotal = 0;
    private String result = "";

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        result = createReport();
        writeToFile(toFileName, result);
    }

    private void readFromFile(String fileName) {
        supplyTotal = 0;
        buyTotal = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splitString = value.split(",");
                if (splitString[NAME_INDEX].equals(SUPPLY)) {
                    supplyTotal += Integer.parseInt(splitString[NUMBER_INDEX]);
                } else if (splitString[NAME_INDEX].equals(BUY)) {
                    buyTotal += Integer.parseInt(splitString[NUMBER_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read the file", e);
        }
    }

    private String createReport() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(",").append(supplyTotal)
                .append(System.lineSeparator()).append(BUY).append(",").append(buyTotal)
                .append(System.lineSeparator()).append(RESULT).append(",")
                .append(supplyTotal - buyTotal);
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to file", e);
        }
    }
}
