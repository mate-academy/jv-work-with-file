package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SPLITTER_REGEX = "\\W+";
    public static final String BUY_STRING_VALUE = "buy";
    public static final String SUPPLY_STRING_VALUE = "supply";
    public static final String RESULT_STRING_VALUE = "result";
    public static final String COMA_STRING = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder resultString = new StringBuilder();
            String value = reader.readLine();

            while (value != null) {
                resultString.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }

            return resultString.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't open file" + fromFileName,e);
        }
    }

    private String createReport(String data) {
        DataCounter dataCounter = new DataCounter();
        dataCounter.countValues(data.split(SPLITTER_REGEX));
        return createStringReport(dataCounter.getBuyAmount(), dataCounter.getSupplyAmount());
    }

    private String createStringReport(int buyAmount, int supplyAmount) {
        return new StringBuilder(SUPPLY_STRING_VALUE + COMA_STRING).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY_STRING_VALUE + COMA_STRING).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT_STRING_VALUE + COMA_STRING).append(supplyAmount - buyAmount)
                .append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file",e);
        }
    }

    private class DataCounter {
        private int buyAmount = 0;
        private int supplyAmount = 0;

        private DataCounter() {
        }

        private void countValues(String[] dataArray) {
            for (int i = 0; i < dataArray.length; i += 2) {
                switch (dataArray[i]) {
                    case BUY_STRING_VALUE -> buyAmount += Integer.parseInt(dataArray[i + 1]);
                    case SUPPLY_STRING_VALUE -> supplyAmount += Integer.parseInt(dataArray[i + 1]);
                    default -> throw new RuntimeException(
                            "Unexpected data in countBuyAndSupplyValues()");
                }
            }
        }

        private int getSupplyAmount() {
            return supplyAmount;
        }

        private int getBuyAmount() {
            return buyAmount;
        }
    }
}
