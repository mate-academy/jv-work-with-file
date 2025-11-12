package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                if (parts.length != 2) {
                    continue;
                }

                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (SUPPLY.equals(operation)) {
                    supplyTotal += amount;
                } else if (BUY.equals(operation)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the reader: " + e.getMessage());
                }
            }
        }

        String report = createReport(supplyTotal, buyTotal);
        writeToFile(toFileName, report);
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder.append(SUPPLY).append(COMMA).append(supply)
                .append(System.getProperty("line.separator")).append(BUY)
                .append(COMMA).append(buy)
                .append(System.getProperty("line.separator"))
                .append(RESULT).append(COMMA).append(result);
        return reportBuilder.toString();
    }

    private void writeToFile(String fileName, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + fileName, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the writer: " + e.getMessage());
                }
            }
        }
    }
}
