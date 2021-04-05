package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String STRING_DIVIDER = " ";
    public static final String WORDS_DIVIDER = ",";
    public static final int AMOUNT_POSITION_INDEX = 1;
    public static final String SUPPLY = "supply,";
    public static final String BUY = "buy,";
    public static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String result = "";
        StringBuilder stringBuilder = new StringBuilder(result);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(STRING_DIVIDER);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
        result = stringBuilder.toString();
        return result;
    }

    private String createReport(String inputData) {
        String[] records = inputData.split(STRING_DIVIDER);
        StringBuilder builder = new StringBuilder();
        int countBuy = 0;
        int countSupply = 0;
        for (String record : records) {
            String[] array = record.split(WORDS_DIVIDER);
            if (record.contains("buy")) {
                countBuy += Integer.parseInt(array[AMOUNT_POSITION_INDEX]);
            } else {
                countSupply += Integer.parseInt(array[AMOUNT_POSITION_INDEX]);
            }
        }
        int result = countSupply - countBuy;
        builder.append(SUPPLY).append(countSupply).append(System.lineSeparator())
                .append(BUY).append(countBuy).append(System.lineSeparator())
                .append(RESULT).append(result).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeToFile(String inputData, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}

