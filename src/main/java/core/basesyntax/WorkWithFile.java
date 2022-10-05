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
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can not create file", e);
        }
        writeToFile(countStatistic(getFromFile(fromFileName)),file.getName());
    }

    private String getFromFile(String fromFilename) {
        try {
            StringBuilder sb;
            try (BufferedReader reader = new BufferedReader(new FileReader(fromFilename))) {
                sb = new StringBuilder();
                int data;
                data = reader.read();
                while (data != -1) {
                    sb.append((char) data);
                    data = reader.read();
                }
            }
            return sb.toString();
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
                supplyCount = supplyCount + Integer.parseInt(strings[i + 1].split("\r\n")[0]);
            } else if (strings[i].contains(BUY)) {
                buyCount = buyCount + Integer.parseInt(strings[i + 1].split("\r\n")[0]);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("supply,").append(supplyCount).append(System.lineSeparator())
                .append("buy,").append(buyCount).append(System.lineSeparator())
                .append("result,").append(supplyCount - buyCount);
        return sb.toString();
    }

    private void writeToFile(String toWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName,true))) {
            writer.write(toWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file " + toFileName, e);
        }
    }
}
