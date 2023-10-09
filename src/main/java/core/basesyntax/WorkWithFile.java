package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(readFile(fromFileName), toFileName);
    }

    private StringBuilder readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        final String splitChar = ",";
        final String supplyString = "supply";
        final String buyString = "buy";
        final String resultString = "result";
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int totalSupply = 0;
            int totalBuy = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(splitChar);
                if (parts.length == 2) {
                    int amount = Integer.parseInt(parts[1].trim());
                    if (parts[0].trim().equals(supplyString)) {
                        totalSupply += amount;
                    } else if (parts[0].trim().equals(buyString)) {
                        totalBuy += amount;
                    }
                }
            }
            int result = totalSupply - totalBuy;
            stringBuilder.append(supplyString)
                    .append(splitChar)
                    .append(totalSupply)
                    .append(System.lineSeparator())
                    .append(buyString)
                    .append(splitChar)
                    .append(totalBuy)
                    .append(System.lineSeparator())
                    .append(resultString)
                    .append(splitChar)
                    .append(result);
            return stringBuilder;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file file: " + fromFileName, e);
        }
    }

    private void writeToFile(StringBuilder someBuilder, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(someBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write in file: " + toFileName,e);
        }
    }
}
