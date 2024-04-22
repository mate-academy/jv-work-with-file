package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFromFile(fromFileName);
        createReport(data, toFileName);
    }

    private int[] readFromFile(String fileName) {
        int[] statistic = new int[3];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operations = parts[0];
                int sum = Integer.parseInt(parts[1]);
                if (OPERATION_SUPPLY.equals(operations)) {
                    statistic[0] += sum;
                } else if (OPERATION_BUY.equals(operations)) {
                    statistic[1] += sum;
                }
            }
            statistic[2] = statistic[0] - statistic[1];
        } catch (IOException e) {
            throw new RuntimeException("Can't read the data from the file" + fileName, e);
        }
        return statistic;
    }

    private void createReport(int[] data, String fileName) {
        int supplyTotal = data[0];
        int buyTotal = data[1];
        int result = data[2];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(OPERATION_SUPPLY + "," + supplyTotal);
            writer.newLine();
            writer.write(OPERATION_BUY + "," + buyTotal);
            writer.newLine();
            writer.write(OPERATION_RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("an't write into the file " + fileName, e);
        }
    }
}

