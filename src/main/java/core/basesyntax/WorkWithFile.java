package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buyTotal = 0;
        int supplyTotal = 0;
        String supply = "supply";
        String buy = "buy";
        String[] lines = readDataFromFile(fromFileName);
        for (String stringInputData : lines) {

            if (stringInputData.split(",")[INDEX_OF_OPERATION_TYPE].equals(buy)) {
                buyTotal += Integer.parseInt(stringInputData.split(",")[INDEX_OF_AMOUNT]);
            }

            if (stringInputData.split(",")[INDEX_OF_OPERATION_TYPE].equals(supply)) {
                supplyTotal += Integer.parseInt(stringInputData.split(",")[INDEX_OF_AMOUNT]);
            }
        }
        int resultAmount = supplyTotal - buyTotal;
        StringBuilder stringReport = new StringBuilder();
        stringReport.append(supply).append(",").append(supplyTotal).append(System.lineSeparator());
        stringReport.append(buy).append(",").append(buyTotal).append(System.lineSeparator());
        stringReport.append("result").append(",").append(resultAmount);
        writeReportToFile(toFileName, stringReport.toString());
    }

    private String[] readDataFromFile(String fromFileName) {
        try {
            File fromFile = new File(fromFileName);
            return Files.readAllLines(fromFile.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        }
    }

    private void writeReportToFile(String toFileName, String stringReport) {
        try {
            File toFile = new File(toFileName);
            Files.writeString(toFile.toPath(), stringReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + toFileName, e);
        }
    }
}
