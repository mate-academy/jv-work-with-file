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

    private String readFromFile(String fromFileName) {
        try {
            return Files.readString(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't open file" + fromFileName,e);
        }
    }

    private String createReport(String data) {
        String[] dataArray = data.split(SPLITTER_REGEX);
        int buyAmount = 0;
        int supplyAmount = 0;

        for (int i = 0; i < dataArray.length; i += 2) {
            switch (dataArray[i]) {
                case BUY_STRING_VALUE -> buyAmount += Integer.parseInt(dataArray[i + 1]);
                case SUPPLY_STRING_VALUE -> supplyAmount += Integer.parseInt(dataArray[i + 1]);
                default -> throw new RuntimeException(
                        "Unexpected data in WorkWithFile.createReport()");
            }
        }
        return createStringReport(buyAmount, supplyAmount);
    }

    private String createStringReport(int buyAmount, int supplyAmount) {
        return "supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator()
                + "result," + (supplyAmount - buyAmount) + System.lineSeparator();
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.writeString(new File(toFileName).toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file",e);
        }
    }
}
