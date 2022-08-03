package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] sumSupplyAndBuy = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] splittedLine = line.split(COMMA);
                int quantity = Integer.parseInt(splittedLine[1]);
                if (splittedLine[0].equals("supply")) {
                    sumSupplyAndBuy[SUPPLY_INDEX] += quantity;
                } else {
                    sumSupplyAndBuy[BUY_INDEX] += quantity;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
        writeToFile(toFileName, createReport(sumSupplyAndBuy));
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    private String createReport(int[] reportData) {
        return "supply,"
                + reportData[SUPPLY_INDEX]
                + System.lineSeparator()
                + "buy,"
                + reportData[BUY_INDEX]
                + System.lineSeparator()
                + "result,"
                + (reportData[SUPPLY_INDEX] - reportData[BUY_INDEX]);
    }
}
