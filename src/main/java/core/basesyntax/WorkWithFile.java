package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buyTotal = 0;
        int supplyTotal = 0;
        final int INDEX_OF_OPERATION_TYPE = 0;
        final int INDEX_OF_AMOUNT = 1;
        final String COMMA = ",";
        final String SUPPLY = "supply";
        final String BUY = "buy";
        final String RESULT = "result";

        String[] strings = readDataFromFile(fromFileName);

        for (String stringInputData : strings) {
            if (stringInputData.split(COMMA)[INDEX_OF_OPERATION_TYPE].equals(BUY)) {
                buyTotal += Integer.parseInt(stringInputData.split(COMMA)[INDEX_OF_AMOUNT]);
            }
            if (stringInputData.split(COMMA)[INDEX_OF_OPERATION_TYPE].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(stringInputData.split(COMMA)[INDEX_OF_AMOUNT]);
            }
        }
        int result = supplyTotal - buyTotal;

        StringBuilder stringReport = new StringBuilder();
        stringReport.append(SUPPLY).append(COMMA).append(supplyTotal)
                .append(System.lineSeparator());
        stringReport.append(BUY).append(COMMA).append(buyTotal).append(System.lineSeparator());
        stringReport.append(RESULT).append(COMMA).append(result);

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
