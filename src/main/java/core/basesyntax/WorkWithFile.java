package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int EXPECTED_PARTS_LENGTH = 2;
    private static final int TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] sums = readFromFile(fromFileName);
        List<String> report = generateReport(sums[0], sums[1]);
        writeReport(toFileName, report);
    }

    private int[] readFromFile(String fromFileName) {
        int sumBuy = 0;
        int sumSupply = 0;
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                if (parts.length < EXPECTED_PARTS_LENGTH) {
                    throw new RuntimeException("Invalid line structure: " + line);
                }
                String type = parts[TYPE_INDEX].trim();
                if (!BUY.equals(type) && !SUPPLY.equals(type)) {
                    throw new RuntimeException("Invalid type '" + type + "' in line: " + line);
                }
                int value;
                try {
                    value = Integer.parseInt(parts[VALUE_INDEX].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number in line: " + line, e);
                }
                if (value < 0) {
                    throw new RuntimeException("Negative value in line: " + line);
                }
                if (BUY.equals(type)) {
                    sumBuy += value;
                } else if (SUPPLY.equals(type)) {
                    sumSupply += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return new int[]{sumSupply, sumBuy};
    }

    private List<String> generateReport(int sumSupply, int sumBuy) {
        List<String> report = new ArrayList<>();
        report.add(SUPPLY + COMMA + sumSupply);
        report.add(BUY + COMMA + sumBuy);
        report.add(RESULT + COMMA + (sumSupply - sumBuy));
        return report;
    }

    private void writeReport(String toFileName, List<String> report) {
        File newFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            for (String line : report) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
