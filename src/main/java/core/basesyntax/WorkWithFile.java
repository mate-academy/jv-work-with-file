package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int WORD_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int BUY_INDEX = 0;
    private static final int SUPPLY_INDEX = 1;
    private static final String COMPARISON_WORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFromFile(fromFileName);
        String report = formatData(readData);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder readString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int fileContent;
            while ((fileContent = reader.read()) != -1) {
                readString.append((char) fileContent);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file" + fileName, e);
        }
        return readString.toString().replaceAll(System.lineSeparator(), " ");
    }

    private int[] calculate(String data) {
        int calculateBuy = 0;
        int calculateSupply = 0;
        String[] calculations = data.split(" ");
        for (int i = 0; i < calculations.length; i++) {
            String[] splitCalculations = calculations[i].split(",");
            int amount = Integer.parseInt(splitCalculations[AMOUNT_INDEX]);
            if (splitCalculations[WORD_INDEX].equals(COMPARISON_WORD)) {
                calculateBuy += amount;
            } else {
                calculateSupply += amount;
            }
        }
        return new int[] { calculateBuy, calculateSupply };
    }

    private String formatData(String extractedData) {
        int[] calculatedData = calculate(extractedData);
        int calculatedBuy = calculatedData[BUY_INDEX];
        int calculatedSupply = calculatedData[SUPPLY_INDEX];
        return new StringBuilder()
                .append("supply,").append(calculatedSupply).append(System.lineSeparator())
                .append("buy,").append(calculatedBuy).append(System.lineSeparator())
                .append("result,").append(calculatedSupply - calculatedBuy).toString();
    }

    private void writeToFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(data);
        } catch (Exception e) {
            throw new RuntimeException("Can't write bytes to file" + fileName, e);
        }
    }
}
