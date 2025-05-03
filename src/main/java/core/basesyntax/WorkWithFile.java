package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int MAX_LENGTH = 2;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {

        int[] totals = readFromFile(fromFileName);
        String result = generateReport(totals);
        writeToFile(result, toFileName);
    }

    private int[] readFromFile(String fromFileName) {
        List<String> fileContent;
        try {
            fileContent = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }

        int supplyTotal = 0;
        int buyTotal = 0;

        for (String data : fileContent) {
            String[] values = data.split(COMMA);
            if (values.length != MAX_LENGTH) {
                throw new IllegalArgumentException("Not true format " + values.length);
            }
            String operation = values[OPERATION_TYPE_INDEX];
            String amount = values[AMOUNT_INDEX];

            try {
                int value = Integer.parseInt(amount);
                if (operation.equals(BUY)) {
                    buyTotal += value;
                } else if (operation.equals(SUPPLY)) {
                    supplyTotal += value;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("File '" + fromFileName
                        + " contains invalid number format: " + amount);
            }
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private String generateReport(int[] totals) {

        int supplyValue = totals[OPERATION_TYPE_INDEX];
        int buyValue = totals[AMOUNT_INDEX];
        int result = supplyValue - buyValue;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY)
                .append(COMMA)
                .append(supplyValue)
                .append(System.lineSeparator());
        report.append(BUY)
                .append(COMMA)
                .append(buyValue)
                .append(System.lineSeparator());
        report.append(RESULT)
                .append(COMMA)
                .append(result)
                .append(System.lineSeparator());

        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);

        try {
            Files.write(toFile.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing the file "
                    + toFileName, e);
        }
    }
}
