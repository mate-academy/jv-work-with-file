package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(fromFileName));
    }

    private String createReport(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String line;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader inputFile = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = inputFile.readLine()) != null) {
                String[] splittedLine = line.split(",");
                if (splittedLine[0].equals("supply")) {
                    supplyTotal += Integer.parseInt(splittedLine[1]);
                } else if (splittedLine[0].equals("buy")) {
                    buyTotal += Integer.parseInt(splittedLine[1]);
                }
            }
            final int result = supplyTotal - buyTotal;
            builder.append("supply,").append(supplyTotal).append(System.lineSeparator())
              .append("buy,").append(buyTotal).append(System.lineSeparator())
              .append("result,").append(result).append(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cant read from file", e);
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file", e);
        }
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

