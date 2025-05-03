package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String SPLIT_PARAMETER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String stringFromFile = readStringFromFile(fromFileName);
        String report = getReport(stringFromFile);
        writeStringToFile(toFileName, report);
    }

    private static String readStringFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private static String getReport(String stringFromFile) {
        String[] arrayFromFile = stringFromFile.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String element : arrayFromFile) {
            String operation = element.split(SPLIT_PARAMETER)[OPERATION_INDEX];
            int amount = Integer.parseInt(element.split(SPLIT_PARAMETER)[AMOUNT_INDEX]);
            if (operation.equals(SUPPLY)) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }
        int resultAmount = supplyAmount - buyAmount;
        return "supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator()
                + "result," + resultAmount;
    }

    private static void writeStringToFile(String fileName, String string) {
        try {
            Files.writeString(Path.of(fileName), string);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}

