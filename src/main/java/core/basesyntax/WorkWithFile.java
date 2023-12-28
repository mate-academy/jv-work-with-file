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
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)))) {
            String value = reader.readLine();
            while (value != null) {
                lines.add(value);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file " + fromFileName, e);
        }
        return String.join("\n", lines);
    }

    private String createReport(String dataFromFile) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = dataFromFile.split("\n");
        StringBuilder reportBuilder = new StringBuilder();

        for (String line : lines) {
            if (line.startsWith(SUPPLY) || line.startsWith(BUY)) {
                String[] parts = line.split(",");
                String numberValue = parts[1];
                int value = Integer.parseInt(numberValue);

                if (line.startsWith(SUPPLY)) {
                    supplyTotal += value;
                } else {
                    buyTotal += value;
                }
            }
        }

        int resultTotal = supplyTotal - buyTotal;
        reportBuilder.append(SUPPLY).append(",").append(supplyTotal).append("\n")
                .append(BUY).append(",").append(buyTotal).append("\n")
                .append(RESULT).append(",").append(resultTotal);

        return reportBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + toFileName, e);
        }
    }
}
