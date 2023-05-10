package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX_FOR_SPLITTING_DATA = "\\W+";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName).split(REGEX_FOR_SPLITTING_DATA);
        String report = countAmountByOperation(lines);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String file) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                text.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return text.toString();
    }

    private String countAmountByOperation(String[] lines) {
        int buyAmount = 0;
        int supplyAmount = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals("buy")) {
                buyAmount += Integer.parseInt(lines[++i]);
            } else if (lines[i].equals("supply")) {
                supplyAmount += Integer.parseInt(lines[++i]);
            }
        }
        StringBuilder string = new StringBuilder();
        return string.append("supply,").append(supplyAmount)
        .append(System.lineSeparator()).append("buy,")
        .append(buyAmount).append(System.lineSeparator())
        .append("result,").append((supplyAmount - buyAmount)).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
