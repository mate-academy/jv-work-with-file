package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File inputCsvFile = new File(fromFileName);
        int totalSupplyAmount = calculateTotalValue(inputCsvFile, "supply");
        int totalBuyAmount = calculateTotalValue(inputCsvFile, "buy");
        writeReport(toFileName, totalSupplyAmount, totalBuyAmount);
    }

    private int calculateTotalValue(File inputFile, String operationType) {
        int totalValue = 0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
            String lineFromFile;

            while ((lineFromFile = fileReader.readLine()) != null) {
                String[] elements = lineFromFile.split(",");

                if (elements[0].equals(operationType)) {
                    totalValue += Integer.parseInt(elements[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file: " + inputFile.getName() + e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file:" + inputFile.getName() + e);
        }
        return totalValue;
    }

    private void writeReport(String toFileName, int totalSupplyAmount, int totalBuyAmount) {
        File outputFile = new File(toFileName);
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file:" + outputFile.getName() + e);
        }

        try (BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFile))) {
            StringBuilder finalOutput = new StringBuilder();
            finalOutput.append("supply,")
                    .append(totalSupplyAmount)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(totalBuyAmount)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(totalSupplyAmount - totalBuyAmount)
                    .append(System.lineSeparator());
            outputFileWriter.write(finalOutput.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + outputFile.getName() + e);
        }
    }
}
