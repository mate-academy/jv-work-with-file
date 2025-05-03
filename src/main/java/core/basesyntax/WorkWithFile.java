package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_POSITION = 0;
    private static final int NUMBER_POSITION = 1;
    private static final int STARTING_NUMBER = 0;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private int supplyTotal = 0;
    private int buyTotal = 0;
    private String report = "";

    public void getStatistic(String fromFileName, String toFileName) {
        supplyTotal = STARTING_NUMBER;
        buyTotal = STARTING_NUMBER;

        readFile(fromFileName);
        createReport();
        writeFile(toFileName);
    }

    private void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[NAME_POSITION].equals(SUPPLY)) {
                    supplyTotal += Integer.parseInt(split[NUMBER_POSITION]);
                } else if (split[NAME_POSITION].equals(BUY)) {
                    buyTotal += Integer.parseInt(split[NUMBER_POSITION]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read the file", e);
        }
    }

    private void createReport() {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(supplyTotal).append(System.lineSeparator())
                    .append(BUY).append(",").append(buyTotal).append(System.lineSeparator())
                    .append(RESULT).append(",").append(supplyTotal - buyTotal);
        report = builder.toString();
    }

    private void writeFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to the file", e);
        }
    }
}
