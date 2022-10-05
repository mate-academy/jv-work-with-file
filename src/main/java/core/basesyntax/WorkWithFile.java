package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(countStatistic(getFromFile(fromFileName)),new File(toFileName).getName());
    }

    private String getFromFile(String fromFilename) {
        try {
            StringBuilder result;
            try (BufferedReader reader = new BufferedReader(new FileReader(fromFilename))) {
                result = new StringBuilder();
                int data;
                data = reader.read();
                while (data != -1) {
                    result.append((char) data);
                    data = reader.read();
                }
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read the line from file " + fromFilename, e);
        }
    }

    public String countStatistic(String operations) {
        String[] strings = operations.split(",");
        int supplyCount = 0;
        int buyCount = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains(SUPPLY)) {
                supplyCount = supplyCount
                        + Integer.parseInt(strings[i + 1].split(System.lineSeparator())[0]);
            } else if (strings[i].contains(BUY)) {
                buyCount = buyCount
                        + Integer.parseInt(strings[i + 1].split(System.lineSeparator())[0]);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("supply,").append(supplyCount).append(System.lineSeparator())
                .append("buy,").append(buyCount).append(System.lineSeparator())
                .append("result,").append(supplyCount - buyCount);
        return sb.toString();
    }

    private void writeToFile(String toWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(toWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file " + toFileName, e);
        }
    }
}
