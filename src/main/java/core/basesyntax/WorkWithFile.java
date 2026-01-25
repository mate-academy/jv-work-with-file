package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        String report = getString(lines);
        writeStringToFile(toFileName, report);
    }

    private static String getString(List<String> lines) {
        int[] supplyBuy = new int[]{0, 0};

        for (String l : lines) {
            supplyBuy(l, supplyBuy);
        }

        String ls = System.lineSeparator();
        StringBuilder sb = new StringBuilder(SUPPLY).append(",").append(supplyBuy[0]).append(ls)
                .append(BUY).append(",").append(supplyBuy[1]).append(ls)
                .append(RESULT).append(",").append(supplyBuy[0] - supplyBuy[1]).append(ls);
        return sb.toString();
    }

    private static void supplyBuy(String l, int[] supplyBuy) {
        if (l == null || l.isBlank()) {
            return;
        }
        String[] data = l.split(",");
        if (data.length != 2) {
            return;
        }
        String op = data[0].trim().toLowerCase();
        int amount = 0;
        try {
            amount = Integer.parseInt(data[1].trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException(data[1] + "is wrong number!");
        }
        switch (op) {
            case SUPPLY:
                supplyBuy[0] += amount;
                break;
            case BUY:
                supplyBuy[1] += amount;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }
    }

    private List<String> readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read from the file "
                    + fileName, e);
        }
        return lines;
    }

    private void writeStringToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Could not write to the file "
                    + fileName, e);
        }
    }
}
