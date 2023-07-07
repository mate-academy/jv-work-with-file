package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = ",";
    private static final String IS_SUPPLY = "supply";
    private static final String IS_BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(REGEX);
                if (fields.length == 2) {
                    String operationType = fields[0];
                    int amount = Integer.parseInt(fields[1]);
                    if (operationType.equals(IS_SUPPLY)) {
                        supplyTotal += amount;
                    } else if (operationType.equals(IS_BUY)) {
                        buyTotal += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        writeStatisticToFile(toFileName, supplyTotal, buyTotal);
    }

    private void writeStatisticToFile(String toFileName,int supplyTotal, int buyTotal) {
        StringBuilder report = new StringBuilder();
        int result = supplyTotal - buyTotal;
        report.append(IS_SUPPLY).append(REGEX).append(supplyTotal).append(System.lineSeparator());
        report.append(IS_BUY).append(REGEX).append(buyTotal).append(System.lineSeparator());
        report.append(RESULT).append(REGEX).append(result).append(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
