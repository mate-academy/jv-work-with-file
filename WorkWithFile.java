package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            int supplyTotal = 0;
            int buyTotal = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operationType = parts[0];
                    int amount = Integer.parseInt(parts[1]);
                    if ("supply".equals(operationType)) {
                        supplyTotal += amount;
                    } else if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }
            int result = supplyTotal - buyTotal;
            writeToFile(writer, "supply", supplyTotal);
            writeToFile(writer, "buy", buyTotal);
            writeToFile(writer, "result", result);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing files", e);
        }
    }

    private void writeToFile(BufferedWriter writer, String operation, int amount) {
        String lineToWrite = operation + "," + amount;
        writeString(writer, lineToWrite);
        writeNewLine(writer);
    }

    private void writeString(BufferedWriter writer, String text) {
        try {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file", e);
        }
    }

    private void writeNewLine(BufferedWriter writer) {
        try {
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file", e);
        }
    }
}
