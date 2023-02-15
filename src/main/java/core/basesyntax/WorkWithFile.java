package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int ENTRY_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final String SPACE_REGEX = " ";
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String COMA_REGEX = ",";
    public static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(getReport(readDataFromFile(fromFileName)), toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        File readFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            while (reader.ready()) {
                builder.append(reader.readLine()).append(SPACE_REGEX);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read data from file " + readFile, exception);
        }
        return builder.toString();
    }

    private String getReport(String data) {
        int result = 0;
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder builder = new StringBuilder();
        String[] info = data.split(SPACE_REGEX);
        for (String line : info) {
            String[] array = line.split(COMA_REGEX);
            if (array[ENTRY_TYPE_INDEX].equals(SUPPLY)) {
                totalSupply += parseInt(array[AMOUNT_INDEX]);
            } else if (array[ENTRY_TYPE_INDEX].equals(BUY)) {
                totalBuy += parseInt(array[AMOUNT_INDEX]);
            }
            result = totalSupply - totalBuy;
        }
        return builder.append(SUPPLY).append(COMA_REGEX)
                .append(totalSupply).append(System.lineSeparator())
                .append(BUY).append(COMA_REGEX).append(totalBuy)
                .append(System.lineSeparator()).append(RESULT_NAME)
                .append(COMA_REGEX).append(result).toString();
    }

    private void writeDataToFile(String formattedData, String direction) {
        File managedData = new File(direction);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(managedData))) {
            for (int i = 0; i < formattedData.length(); i++) {
                writer.write(formattedData.charAt(i));
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't write data to file " + managedData, exception);
        }
    }
}
