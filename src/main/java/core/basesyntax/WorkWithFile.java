package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] sum = readDataFromFile(fromFileName);
        String report = createReport(sum[0], sum[1]);
        writeDataToFile(toFileName, report);
    }

    private int[] readDataFromFile(String fromFileName) {

        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                int amount = Integer.parseInt(parts[1]);
                if (parts[0].equals(BUY)) {
                    buySum += amount;
                } else if (parts[0].equals(SUPPLY)) {
                    supplySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        return new int[]{supplySum, buySum};
    }

    private String createReport(int supplySum, int buySum) {
        int result = supplySum - buySum;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                .append(COMMA)
                .append(supplySum)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(buySum)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(result);
        return stringBuilder.toString();
    }

    private void writeDataToFile(String toFileName, String data) {
        try {
            Files.writeString(Path.of(toFileName), data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }

    }
}
