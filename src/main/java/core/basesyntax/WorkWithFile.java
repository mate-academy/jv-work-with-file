package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String REPORT_HEADER_SUPPLY = "supply";
    private static final String REPORT_HEADER_BUY = "buy";
    private static final String REPORT_HEADER_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            String line;
            int supplyTotal = 0;
            int buyTotal = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());

                    if (operation.equals(OPERATION_SUPPLY)) {
                        supplyTotal += amount;
                    } else if (operation.equals(OPERATION_BUY)) {
                        buyTotal += amount;
                    }
                }
            }

            int result = supplyTotal - buyTotal;

            writer.write(REPORT_HEADER_SUPPLY + DELIMITER + supplyTotal);
            writer.newLine();
            writer.write(REPORT_HEADER_BUY + DELIMITER + buyTotal);
            writer.newLine();
            writer.write(REPORT_HEADER_RESULT + DELIMITER + result);

            reader.close();
            writer.close();

            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }
}
