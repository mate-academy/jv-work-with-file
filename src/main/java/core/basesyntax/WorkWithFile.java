package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";


    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String report;
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(",");
                String typeOfOperation = parts[0];
                int sum = Integer.parseInt(parts[1]);
                if (typeOfOperation.equals(SUPPLY)) {
                    supply += sum;
                } else {
                    buy += sum;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        report = groupBy(stringBuilder, supply, buy);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            try {
                writer.write(report);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file" + toFileName, e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    private String groupBy(StringBuilder stringBuilder, int supply, int buy) {
        stringBuilder.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(",").append(buy)
                .append(System.lineSeparator()).append(RESULT).append(",").append(supply - buy);
         return stringBuilder.toString();
    }
}
