package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public static void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            int[] totals = {0, 0};

            String line;
            while ((line = readLine(reader)) != null) {
                String[] fields = parseFields(line);
                if (fields.length == 2) {
                    String operationType = fields[0].trim();
                    int amount = Integer.parseInt(fields[1].trim());
                    updateTotals(operationType, amount, totals);
                }
            }

            String report = formReport(totals[0], totals[1]);
            writeToFile(writer, report);

        } catch (IOException e) {
            handleException(e, fromFileName);
        }
    }

    private static String readLine(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    private static String[] parseFields(String line) {
        return line.split(",");
    }

    private static void updateTotals(String operationType, int amount, int[] totals) {
        if (SUPPLY_OPERATION.equals(operationType)) {
            totals[0] += amount; // Update supplyTotal
        } else if (BUY_OPERATION.equals(operationType)) {
            totals[1] += amount; // Update buyTotal
        }
    }

    private static String formReport(int supplyTotal, int buyTotal) {
        return String.format("supply,%d%nbuy,%d%nresult,%d", supplyTotal,
                buyTotal, (supplyTotal - buyTotal));
    }

    private static void writeToFile(BufferedWriter writer, String report) throws IOException {
        writer.write(report);
    }

    private static void handleException(IOException e, String fileName) {
        throw new RuntimeException("Error processing file: " + fileName, e);
    }
}
