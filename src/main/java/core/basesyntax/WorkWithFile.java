package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int AMOUNT_INDEX = 1;
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int SUPPLY_DATA_INDEX = 0;
    public static final int VALUE_FROM_DATA_INDEX = 1;
    public static final int BUY_DATA_INDEX = 1;
    public static final int SUPPLY_DATA_IN_REPORT_INDEX = 0;
    public static final int BUY_DATA_IN_REPORT_INDEX = 1;
    public static final int RESULT_DATA_IN_REPORT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReport(toFileName, report);
    }

    private String readFile(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                String operationType = data[OPERATION_TYPE_INDEX].trim();
                int amount = Integer.parseInt(data[AMOUNT_INDEX].trim());

                if ("supply".equals(operationType)) {
                    totalSupply += amount;
                } else if ("buy".equals(operationType)) {
                    totalBuy += amount;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }

        return "Total Supply:" + totalSupply + ";Total Buy:" + totalBuy;
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        if (data.split(";")[SUPPLY_DATA_INDEX] != null
                && data.split(";")[SUPPLY_DATA_INDEX]
                .split(":")[VALUE_FROM_DATA_INDEX] != null) {
            supply = Integer.parseInt(data.split(";")[SUPPLY_DATA_INDEX]
                    .split(":")[VALUE_FROM_DATA_INDEX]);
        }
        if (data.split(";")[BUY_DATA_INDEX] != null
                && data.split(";")[BUY_DATA_INDEX]
                .split(":")[VALUE_FROM_DATA_INDEX] != null) {
            buy = Integer.parseInt(data.split(";")[BUY_DATA_INDEX]
                    .split(":")[VALUE_FROM_DATA_INDEX]);
        }
        result = supply - buy;

        return "supply," + supply + ";buy," + buy + ";result," + result;
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String[] reportParts = report.split(";");

            writer.write(reportParts[SUPPLY_DATA_IN_REPORT_INDEX]);
            writer.newLine();
            writer.write(reportParts[BUY_DATA_IN_REPORT_INDEX]);
            writer.newLine();
            writer.write(reportParts[RESULT_DATA_IN_REPORT_INDEX]);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }
}
