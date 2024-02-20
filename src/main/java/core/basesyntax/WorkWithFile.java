package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line)
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    private String createReport(String dataFromFile) {
        int supplyTotal = 0;
        int buyTotal = 0;
        StringBuilder reportBuilder = new StringBuilder();

        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String operationType = parts[OPERATION_TYPE_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);
                if ("supply".equals(operationType)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operationType)) {
                    buyTotal += amount;
                }
            }
        }

        int result = supplyTotal - buyTotal;

        reportBuilder.append("supply,")
                .append(supplyTotal)
                .append(System.lineSeparator());
        reportBuilder.append("buy,")
                .append(buyTotal)
                .append(System.lineSeparator());
        reportBuilder.append("result,")
                .append(result);

        return reportBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
