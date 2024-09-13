package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String DELIMITER = ",";
    public static final String OPERATION = "buy";
    private int supplyAmount = 0;
    private int buyAmount = 0;
    private final StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        supplyAmount = 0;
        buyAmount = 0;
        File fromFile = new File(fromFileName);
        final File toFile = new File(toFileName);

        readDataFromTheFile(fromFile);

        int result = supplyAmount - buyAmount;

        stringBuilder.setLength(0);
        stringBuilder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(result);

        writeReportToFile(toFile);
    }

    private void readDataFromTheFile(File fromFile) {
        List<String> fileData;
        try {
            fileData = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        for (String data : fileData) {
            String[] values = data.split(DELIMITER);

            if (values.length != 2) {
                continue;
            }

            String operation = values[0];
            String amountString = values[1];

            try {
                int value = Integer.parseInt(amountString);
                if (operation.equals(OPERATION)) {
                    buyAmount += value;
                } else {
                    supplyAmount += value;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in file: " + amountString);
            }
        }
    }

    private void writeReportToFile(File toFile) {
        try {
            Files.write(toFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
