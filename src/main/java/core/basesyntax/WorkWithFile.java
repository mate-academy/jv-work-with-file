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

    public void getStatistic(String fromFileName, String toFileName) {
        int[] output = readFile(fromFileName);
        String result = getResult(output);
        writeToFile(result, toFileName);
    }

    private int[] readFile(String fromFileName) {
        List<String> fileData;
        try {
            fileData = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        int supplyAmount = 0;
        int buyAmount = 0;

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
                } else if (operation.equals(SUPPLY)) {
                    supplyAmount += value;
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format in file: " + amountString);
            }
        }

        return new int[]{supplyAmount, buyAmount};
    }

    private String getResult(int[] output) {
        int supplyAmount = output[0];
        int buyAmount = output[1];
        int resultAmount = supplyAmount - buyAmount;

        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(resultAmount)
                .toString();
    }

    private void writeToFile(String result, String toFileName) {
        File toFile = new File(toFileName);
        try {
            Files.write(toFile.toPath(), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
