package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String REGEX = " ";
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT_REGEX = ",";
    public static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName, toFileName);
        String unformedData = readDataFromFile(fromFileName, toFileName);
        String formatData = getFormattedData(unformedData);
        writeDataToFile(formatData, toFileName);

    }

    private String readDataFromFile(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            while (reader.ready()) {
                builder.append(reader.readLine()).append(REGEX);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read data from file " + readFile, exception);
        }
        return builder.toString();
    }

    private String getFormattedData(String data) {
        int result;
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder builder = new StringBuilder();
        String[] arr = data.split(REGEX);
        for (String tips : arr) {
            final int intValue = parseInt(tips.substring(tips.indexOf(RESULT_REGEX) + 1));
            if (tips.contains(BUY)) {
                totalBuy += intValue;
            } else if (tips.contains(SUPPLY)) {
                totalSupply += intValue;
            }
        }
        result = totalSupply - totalBuy;
        builder.append(SUPPLY).append(RESULT_REGEX)
                .append(totalSupply).append(System.lineSeparator())
                .append(BUY).append(RESULT_REGEX).append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT_NAME).append(RESULT_REGEX).append(result);
        return builder.toString();
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
