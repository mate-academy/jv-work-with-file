package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public static final String SPLITTER_REGEX = "\\W+";
    public static final String BUY_STRING_VALUE = "buy";
    public static final String SUPPLY_STRING_VALUE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
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

    private String readFromFile(String fromFileName) {
        try {
            return Files.readString(new File(fromFileName).toPath());
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
        return new StringBuilder("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount).append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.writeString(new File(toFileName).toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }
}
