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
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            return data.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    private String createReport(String data) {
        int buyTotal = 0;
        int supplyTotal = 0;

        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length == 2) {
                String operationType = fields[OPERATION_TYPE_INDEX].trim().toLowerCase();
                int amount = Integer.parseInt(fields[AMOUNT_INDEX].trim());

                if ("buy".equals(operationType)) {
                    buyTotal += amount;
                } else if ("supply".equals(operationType)) {
                    supplyTotal += amount;
                }
            }
        }

        return String.format("supply,%d%nbuy,%d%nresult,%d%n",
                supplyTotal, buyTotal, (supplyTotal - buyTotal));
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }
}
