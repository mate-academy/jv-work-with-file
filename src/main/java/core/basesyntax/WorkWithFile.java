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
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = processFileData(fromFileName, SUPPLY);
        int buy = processFileData(fromFileName, BUY);

        writeToFile(toFileName, supply, buy);
    }

    private int processFileData(String fileName, String operationType) {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parse = line.split(COMMA);
                String name = parse[0];
                int value = Integer.parseInt(parse[1]);
                if (name.equals(operationType)) {
                    total += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return total;
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.append(SUPPLY).append(COMMA).append(String.valueOf(supply)).append("\n");
            writer.append(BUY).append(COMMA).append(String.valueOf(buy)).append("\n");
            writer.append(RESULT).append(COMMA).append(String.valueOf(supply - buy)).append("\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
