package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public static final String SPLIT = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeIntoFile(toFileName, calculateReport(fromFileName));
    }

    private static int[] readFromFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SPLIT);
                if (parts[0].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(parts[1]);
                } else if (parts[0].equals(BUY)) {
                    buySum += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supplySum, buySum};
    }

    private String calculateReport(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] current = readFromFile(fromFileName);
        int supply = current[0];
        int buy = current[1];
        int result = current[0] - current[1];
        return stringBuilder.append(SUPPLY)
                .append(SPLIT).append(supply).append(System.lineSeparator()).append(BUY)
                .append(SPLIT).append(buy).append(System.lineSeparator()).append(RESULT)
                .append(SPLIT).append(result).toString();
    }

    private void writeIntoFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}


