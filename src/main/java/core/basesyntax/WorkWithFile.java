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
    private int result = 0;
    private String operation;
    private String amount;
    private List<String> fileData = null;
    private final StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        readDataFromTheFile(fromFile);

        result = supplyAmount - buyAmount;

        stringBuilder.append("supply, ").append(supplyAmount).append(System.lineSeparator())
                .append("buy, ").append(buyAmount).append(System.lineSeparator())
                .append("result, ").append(result);

        writeToTheFile(toFile);
    }

    public void readDataFromTheFile(File fromFile) {
        try {
            fileData = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file", e);
        }

        for (String data : fileData) {
            String[] values = data.split(DELIMITER);

            operation = values[0];
            amount = values[1];

            if (operation.equals(OPERATION)) {
                buyAmount += Integer.parseInt(amount);
            } else {
                supplyAmount += Integer.parseInt(amount);
            }
        }
    }

    public void writeToTheFile(File toFile) {
        try {
            Files.write(toFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cant write to the file", e);
        }
    }
}
