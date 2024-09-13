package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * The WorkWithFile class processes input files to calculate and record
 * statistics about supply and buy operations.
 */
public class WorkWithFile {
    public static final String DELIMITER = ",";
    public static final String OPERATION = "buy";
    private int supplyAmount = 0;
    private int buyAmount = 0;
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Reads data from a file, calculates statistics, and writes the result to another file.
     *
     * @param fromFileName the name of the file to read data from
     * @param toFileName the name of the file to write the statistics to
     */
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

        writeToTheFile(toFile);
    }

    /**
     * Reads data from a file and updates supply and buy amounts.
     *
     * @param fromFile the file to read data from
     */
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
                // Skip invalid lines
                continue;
            }

            String operation = values[0];
            String amount = values[1];

            try {
                int value = Integer.parseInt(amount);
                if (operation.equals(OPERATION)) {
                    buyAmount += value;
                } else {
                    supplyAmount += value;
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format
                System.err.println("Invalid number format in file: " + amount);
            }
        }
    }

    /**
     * Writes the calculated statistics to a file.
     *
     * @param toFile the file to write data to
     */
    private void writeToTheFile(File toFile) {
        try {
            Files.write(toFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
