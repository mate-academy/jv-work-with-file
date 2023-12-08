package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMETER = " ";
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String generateReport(String data) {
        String[] dividedByEmptySpace = data.split(DELIMETER);
        int supplySum = 0;
        int buySum = 0;
        for (String info : dividedByEmptySpace) {
            String[] dividedActionAndAmount = info.split(COMMA_SEPARATOR);
            String action = dividedActionAndAmount[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(dividedActionAndAmount[AMOUNT_INDEX]);
            if (action.equals("supply")) {
                supplySum += amount;
            } else {
                buySum += amount;
            }
        }
        int benefit = supplySum - buySum;
        StringBuilder result = new StringBuilder("supply,").append(supplySum)
                .append(System.lineSeparator()).append("buy,").append(buySum)
                .append(System.lineSeparator()).append("result,").append(benefit);
        return result.toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder fullFileText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fullFileText.append(line).append(DELIMETER);
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read data from file ", e);
        }
        return fullFileText.toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write data to file ", e);
        }
    }
}
