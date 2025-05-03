package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String PARSED_STRING_SEPARATOR = ";";
    private static final String FORMED_STRING_SEPARATOR = ",";
    private static final String BUY_CATEGORY_NAME = "buy";
    private static final String SUPPLY_CATEGORY_NAME = "supply";
    private static final byte OPERATION_AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readDataFromFile(fromFileName);
        String report = formReport(data);
        writeFile(toFileName, report);
    }

    public String[] readDataFromFile(String fromFileName) {
        StringBuilder parsedCsv = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                parsedCsv.append(value).append(PARSED_STRING_SEPARATOR);
            }

            return parsedCsv.toString().split(PARSED_STRING_SEPARATOR);
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String formReport(String[] parsedString) {
        int buyAmount = 0;
        int supplyAmount = 0;
        StringBuilder result = new StringBuilder();

        for (String data: parsedString) {
            String[] records = data.split(FORMED_STRING_SEPARATOR);
            int amountNumber = Integer.parseInt(records[OPERATION_AMOUNT_INDEX]);

            if (data.contains(BUY_CATEGORY_NAME)) {
                buyAmount += amountNumber;
            } else if (data.contains(SUPPLY_CATEGORY_NAME)) {
                supplyAmount += amountNumber;
            }
        }

        return result
                .append(SUPPLY_CATEGORY_NAME)
                .append(FORMED_STRING_SEPARATOR)
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY_CATEGORY_NAME)
                .append(FORMED_STRING_SEPARATOR)
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result")
                .append(FORMED_STRING_SEPARATOR)
                .append(supplyAmount - buyAmount)
                .toString();
    }

    private void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
