package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private int[] readFromFile(String fromFileName) {
        try (BufferedReader inputFile = new BufferedReader(new FileReader(fromFileName))) {
            final String coma_separator = ",";
            final String supply_word = "supply";
            final String buy_word = "buy";
            final int operation_type_index = 0;
            final int amount_index = 1;
            int supplyTotal = 0;
            int buyTotal = 0;
            String line;
            while ((line = inputFile.readLine()) != null) {
                String[] splittedLine = line.split(coma_separator);
                if (splittedLine[operation_type_index].equals(supply_word)) {
                    supplyTotal += Integer.parseInt(splittedLine[amount_index]);
                } else if (splittedLine[operation_type_index].equals(buy_word)) {
                    buyTotal += Integer.parseInt(splittedLine[amount_index]);
                }
            }
            return new int[] {supplyTotal, buyTotal};
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file", e);
        }
    }

    private String createReport(int[] totals) {
        final int result = totals[0] - totals[1];
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(totals[0]).append(System.lineSeparator())
              .append("buy,").append(totals[1]).append(System.lineSeparator())
              .append("result,").append(result).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(toFileName))) {
            outputFile.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file", e);
        }
    }
}

