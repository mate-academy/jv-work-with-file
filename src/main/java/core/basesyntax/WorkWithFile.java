package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int EXPECTED_VALUES_SIZE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int [] fromFileData = readMethod(fromFileName);
        String result = getResult(fromFileData);
        createNewFile(result, toFileName);

    }

    public int[] readMethod(String fromFileName) {

        List<String> fileData;
        try {
            fileData = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }

        int supplyAmount = 0;
        int buyAmount = 0;

        for (String data : fileData) {
            String[] values = data.split(COMMA);

            if (values.length != 2) {
                throw new RuntimeException("Invalid input value");
            }
            String operation = values[0];
            String amount = values[1];

            if (values.length == EXPECTED_VALUES_SIZE) {
                try {
                    int value = Integer.parseInt(amount);
                    if (operation.equals(BUY)) {
                        buyAmount += value;
                    } else if (operation.equals(SUPPLY)) {
                        supplyAmount += value;
                    }
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number format", e);
                }
            }
        }
        return new int[] {buyAmount, supplyAmount};
    }

    public String getResult(int[] fromFileData) {

        StringBuilder builder = new StringBuilder();

        int buyAmount = fromFileData[0];
        int supplyAmount = fromFileData[1];
        int resultAmount = supplyAmount - buyAmount;

        return builder
                .append(SUPPLY).append(COMMA).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(resultAmount).toString();

    }

    public void createNewFile(String result, String toFileName) {
        File newFile = new File(toFileName);
        try {
            Files.write(newFile.toPath(), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't create a new file", e);
        }
    }
}
