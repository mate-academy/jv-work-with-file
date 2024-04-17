package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class WorkWithFile {
    private static final String SUPPLY_TEXT = "supply";
    private static final String BUY_TEXT = "buy";
    private static final String RESULT_TEXT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = getData(fromFileName);
        createReport(toFileName, data[0], data[1]);
    }

    public void createReport(String filename, int buy, int supply) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            StringBuilder reportBuilder = new StringBuilder();
            String reportString = reportBuilder.append(SUPPLY_TEXT)
                    .append(",")
                    .append(supply)
                    .append(System.lineSeparator())
                    .append(BUY_TEXT)
                    .append(",")
                    .append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT_TEXT)
                    .append(",")
                    .append(supply - buy)
                    .toString();
            writer.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read write file: " + e.getMessage());
        }
    }

    public int[] getData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int buyVal = 0;
            int supplyVal = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split(",");
                if (Objects.equals(lineArr[0], "buy")) {
                    buyVal += Integer.parseInt(lineArr[1]);
                } else if (Objects.equals(lineArr[0], "supply")) {
                    supplyVal += Integer.parseInt(lineArr[1]);
                }
            }
            return new int[]{buyVal, supplyVal};
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file: " + e.getMessage());
        }
    }
}
