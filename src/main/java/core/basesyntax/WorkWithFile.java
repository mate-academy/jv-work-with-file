package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT = "result";
    private static final String DIVIDER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputString = readFile(fromFileName);
        int[] amounts = countAmounts(inputString);
        byte[] report = makeReport(amounts);
        writeReport(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File inputFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find File!", e);
        }
        return builder.toString();
    }

    private int[] countAmounts(String inputString) {
        int countOrders = 0;
        int countSupplies = 0;
        String[] splitLines = inputString.split(System.lineSeparator());
        for (String line:splitLines) {
            String[] splitValue = line.split(DIVIDER);
            if (splitValue[INDEX_OF_OPERATION].equals(BUY_OPERATION)) {
                countOrders += Integer.parseInt(splitValue[INDEX_OF_AMOUNT]);
            } else if (splitValue[INDEX_OF_OPERATION].equals(SUPPLY_OPERATION)) {
                countSupplies += Integer.parseInt(splitValue[INDEX_OF_AMOUNT]);
            }
        }
        return new int[]{countSupplies,countOrders};
    }

    private byte[] makeReport(int [] amounts) {
        final int indexOfSupplies = 0;
        final int indexOfOrders = 1;
        int countSupplies = amounts[indexOfSupplies];
        int countOrders = amounts[indexOfOrders];

        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_OPERATION).append(DIVIDER).append(countSupplies);
        builder.append(System.lineSeparator());
        builder.append(BUY_OPERATION).append(DIVIDER).append(countOrders);
        builder.append(System.lineSeparator());
        builder.append(RESULT).append(DIVIDER).append(countSupplies - countOrders);
        String report = builder.toString();

        return report.getBytes(StandardCharsets.UTF_8);
    }

    private void writeReport(String toFileName, byte[] report) {
        try {
            Files.write(Paths.get(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to File",e);
        }
    }
}
