package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeReportToFile(report, toFileName);
    }

    private String createReport(String fromFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(COMMA);
                if (split.length == 2) {
                    String operationType = split[OPERATION_TYPE_INDEX];
                    String amountStr = split[AMOUNT_INDEX];

                    if (operationType.equals(SUPPLY_OPERATION)) {
                        supplyAmount += Integer.parseInt(amountStr);
                    } else if (operationType.equals(BUY_OPERATION)) {
                        buyAmount += Integer.parseInt(amountStr);
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        int result = supplyAmount - buyAmount;

        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_OPERATION).append(COMMA)
                .append(supplyAmount).append(System.lineSeparator());
        builder.append(BUY_OPERATION).append(COMMA)
                .append(buyAmount).append(System.lineSeparator());
        builder.append(RESULT_OPERATION).append(COMMA).append(result);

        return builder.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
