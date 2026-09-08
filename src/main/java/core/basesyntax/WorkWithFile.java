package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SUPPLY_REPORT = "supply,";
    private static final String BUY_REPORT = "buy,";
    private static final String RESULT_REPORT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readData(fromFileName);
        String report = createReport(data[0], data[1], data[2]);
        writeReport(toFileName, report);
    }

    private int[] readData(String fromFileName) {
        int amountSupply = 0;
        int amountBuy = 0;
        int result;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(",");
                if (parts[0].equals(SUPPLY)) {
                    amountSupply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals(BUY)) {
                    amountBuy += Integer.parseInt(parts[1]);
                }
            }
            result = amountSupply - amountBuy;
            return new int[]{amountSupply, amountBuy, result};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String createReport(int amountSupply, int amountBuy, int result) {
        return SUPPLY_REPORT + amountSupply + "\n"
                + BUY_REPORT + amountBuy + "\n"
                + RESULT_REPORT + result;
    }

    private void writeReport(String toFileName, String report) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
