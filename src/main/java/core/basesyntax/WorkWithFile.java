package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumOfBuying = 0;
        int sumOfSupply = 0;
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String lineFromFile = reader.readLine();
            while (lineFromFile != null) {
                String operationType = lineFromFile.substring(0,lineFromFile.indexOf(','));
                int data = Integer.parseInt(lineFromFile.substring(lineFromFile.indexOf(',') + 1));
                if (operationType.equals("buy")) {
                    sumOfBuying += data;
                } else if (operationType.equals("supply")) {
                    sumOfSupply += data;
                }
                lineFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + e);
        }
        int result = sumOfSupply - sumOfBuying;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(sumOfSupply).append(System.lineSeparator())
                .append("buy,").append(sumOfBuying).append(System.lineSeparator())
                .append("result,").append(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e);
        }
    }
}
