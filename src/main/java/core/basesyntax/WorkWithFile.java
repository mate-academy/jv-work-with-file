package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    private int supplyAmount = 0;
    private int buyAmount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        supplyAmount = 0;
        buyAmount = 0;

        restul = readFromFile(fromFile);
        report = calculateReport(fromFile, stringBuilder);
        writeIntoFile(toFile, report);

        writeIntoFile(toFile, stringBuilder);
    }

    private String calculateReport(File fromFile, StringBuilder stringBuilder) {


        stringBuilder.append(SUPPLY).append(COMMA).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplyAmount - buyAmount);
    }

    private void readFromFile(File fromFile) {
        List<String> fileData;
        try {
            fileData = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        for (String data : fileData) {
            String[] values = data.split(COMMA);

            if (values.length != 2) {
                continue;
            }

            String operation = values[0];
            String amountString = values[1];

            try {
                int value = Integer.parseInt(amountString);
                if (operation.equals(BUY)) {
                    buyAmount += value;
                } else {
                    supplyAmount += value;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in file: " + amountString);
            }
        }
    }

    private void writeIntoFile(File toFile, StringBuilder stringBuilder) {

        try {
            Files.write(toFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
