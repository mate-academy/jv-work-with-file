package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        // Variables to store the total supply and buy amounts
        int supply = 0;
        int buy = 0;
        int result;
        // Step 1: Read the CSV file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split(",");
                try {
                    int value = Integer.parseInt(lineSplit[1].trim());
                    String lineEach = lineSplit[0].trim();
                    if (lineEach.equals("supply")) {
                        supply += value;
                    } else if (lineEach.equals("buy")) {
                        buy += value;
                    }
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number format", e);
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Error in reading CSV file", e);
        }

        StringBuilder builderReport = new StringBuilder();
        builderReport.append("supply,").append(supply).append(System.lineSeparator());
        builderReport.append("buy,").append(buy).append(System.lineSeparator());
        builderReport.append("result,").append(result).append(System.lineSeparator());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(builderReport.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error in writing file", e);
        }
    }
}
