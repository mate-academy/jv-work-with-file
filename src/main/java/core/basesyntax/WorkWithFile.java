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
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        String report;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String[] lines = builder.toString().split(System.lineSeparator());
            int supplySum = 0;
            int buySum = 0;
            for (String line : lines) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    String operation = fields[0].trim();
                    int amount = Integer.parseInt(fields[1].trim());
                    if (operation.equals("supply")) {
                        supplySum += amount;
                    } else if (operation.equals("buy")) {
                        buySum += amount;
                    }
                }
            }
            int result = supplySum - buySum;

            report = "supply," + supplySum + System.lineSeparator()
                    + "buy," + buySum + System.lineSeparator()
                    + "result," + result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
