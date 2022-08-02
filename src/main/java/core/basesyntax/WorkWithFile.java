package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SPLIT_REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] sumSupplyAndBuy = new int[2];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(SPLIT_REGEX);
                int quantity = Integer.parseInt(lineSplit[1]);
                if (lineSplit[0].equals("supply")) {
                    sumSupplyAndBuy[SUPPLY_INDEX] += quantity;
                } else {
                    if (lineSplit[0].equals("buy")) {
                        sumSupplyAndBuy[BUY_INDEX] += quantity;
                    }
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

    private String createReport(int[] resultSupplyAndBuy) {
        return "supply,"
                + resultSupplyAndBuy[SUPPLY_INDEX]
                + System.lineSeparator()
                + "buy,"
                + resultSupplyAndBuy[BUY_INDEX]
                + System.lineSeparator()
                + "result,"
                + (resultSupplyAndBuy[SUPPLY_INDEX] - resultSupplyAndBuy[BUY_INDEX]);
    }
}
