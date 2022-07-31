package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY_OPERATION = "supply";
    public static final String BUY_OPERATION = "buy";
    public static final String RESULT_CALCULATION = "result";
    public static final int OPERATION_TYPE_COLUMN = 0;
    public static final int AMOUNT_COLUMN = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        String[] reportLines = report.split(System.lineSeparator());
        File file = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (String line : reportLines) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }

    private String createReport(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }

        String[] entries = builder.toString().split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        int result = 0;

        for (int i = 0; i < entries.length; i++) {
            String[] entryData = entries[i].split(",");
            String operationType = entryData[OPERATION_TYPE_COLUMN];
            if (operationType.equals(SUPPLY_OPERATION)) {
                supplyAmount += Integer.parseInt(entryData[AMOUNT_COLUMN]);
            } else if (operationType.equals(BUY_OPERATION)) {
                buyAmount += Integer.parseInt(entryData[AMOUNT_COLUMN]);
            }
        }

        result = supplyAmount - buyAmount;
        builder.setLength(0);
        builder.append(SUPPLY_OPERATION)
                .append(",")
                .append(supplyAmount)
                .append(System.lineSeparator());
        builder.append(BUY_OPERATION)
                .append(",")
                .append(buyAmount)
                .append(System.lineSeparator());
        builder.append(RESULT_CALCULATION)
                .append(",")
                .append(result);

        return builder.toString();
    }
}
