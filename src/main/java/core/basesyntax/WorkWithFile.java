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

            int supplyTotal = 0;
            int buyTotal = 0;

            String line;
            while ((line = readFile(reader)) != null) {
                String[] fields = line.split(",");

                if (fields.length == 2) {
                    String operationType = fields[0].trim();
                    int amount = Integer.parseInt(fields[1].trim());

                    if (SUPPLY_OPERATION.equals(operationType)) {
                        supplyTotal += amount;
                    } else if (BUY_OPERATION.equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }

            String report = formReport(supplyTotal, buyTotal);
            writeToFile(writer, report);

        } catch (IOException e) {
            handleException(e, fromFileName);
        }
    }

    private static String readFile(BufferedReader reader) throws IOException {
        return reader.readLine();
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
