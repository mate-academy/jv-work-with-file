package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> statistics = readData(fromFileName);
        String report = createReport(statistics);
        writeReport(report, toFileName);
    }

    private Map<String, Integer> readData(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals("supply")) {
                    supplyTotal += amount;
                } else if (operation.equals("buy")) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("supply", supplyTotal);
        statistics.put("buy", buyTotal);
        statistics.put("result", supplyTotal - buyTotal);
        return statistics;
    }

    private String createReport(Map<String, Integer> statistics) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(statistics.get("supply"))
                .append(System.lineSeparator());
        reportBuilder.append("buy,").append(statistics.get("buy")).append(System.lineSeparator());
        reportBuilder.append("result,").append(statistics.get("result"));
        return reportBuilder.toString();
    }

    private void writeReport(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
