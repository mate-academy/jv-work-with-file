package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buyTotal = 0;
        int supplyTotal = 0;
        final int indexOfOperationType = 0;
        final int indexOfAmount = 1;
        final String comma = ",";
        final String supply = "supply";
        final String buy = "buy";

        String[] strings = readDataFromFile(fromFileName);

        for (String stringInputData : strings) {
            if (stringInputData.split(comma)[indexOfOperationType].equals(buy)) {
                buyTotal += Integer.parseInt(stringInputData.split(comma)[indexOfAmount]);
            }
            if (stringInputData.split(comma)[indexOfOperationType].equals(supply)) {
                supplyTotal += Integer.parseInt(stringInputData.split(comma)[indexOfAmount]);
            }
        }
        int resultAmount = supplyTotal - buyTotal;

        StringBuilder stringReport = new StringBuilder();
        stringReport.append(supply).append(comma).append(supplyTotal)
                .append(System.lineSeparator());
        stringReport.append(buy).append(comma).append(buyTotal).append(System.lineSeparator());
        stringReport.append("result").append(comma).append(resultAmount);

        try {
            File toFile = new File(toFileName);
            Files.writeString(toFile.toPath(), stringReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + toFileName, e);
        }
    }

    private String[] readDataFromFile(String fromFileName) {
        try {
            File fromFile = new File(fromFileName);
            return Files.readAllLines(fromFile.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        }
    }
}
