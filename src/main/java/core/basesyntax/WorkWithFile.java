package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String FIELD_SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int AMOUNT_OF_SUPPLY_INDEX = 0;
    private static final int AMOUNT_OF_BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[]amounts = calculateAmounts(fromFileName);
        String report = createReport(amounts);
        writeToFile(toFileName, report);
    }

    private int[] calculateAmounts(String fromFileName) {
        int amountOfSupply = 0;
        int amountOfBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] operationData = value.split(FIELD_SEPARATOR);
                if (operationData[OPERATION_INDEX].equals(SUPPLY)) {
                    amountOfSupply += Integer.parseInt(operationData[AMOUNT_INDEX]);
                } else {
                    amountOfBuy += Integer.parseInt(operationData[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return new int[]{amountOfSupply, amountOfBuy};
    }

    private String createReport(int[] amounts) {
        return SUPPLY + FIELD_SEPARATOR + amounts[AMOUNT_OF_SUPPLY_INDEX]
                + System.lineSeparator()
                + BUY + FIELD_SEPARATOR + amounts[AMOUNT_OF_BUY_INDEX]
                + System.lineSeparator()
                + RESULT + FIELD_SEPARATOR + (amounts[AMOUNT_OF_SUPPLY_INDEX]
                - amounts[AMOUNT_OF_BUY_INDEX]);
    }

    private void writeToFile(String toFileName, String report) {
        try {
            File file = new File(toFileName);
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
