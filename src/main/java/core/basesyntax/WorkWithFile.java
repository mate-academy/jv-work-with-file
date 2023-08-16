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

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readDataFromFile(fromFileName);
        String report = formReport(data);
        createNewDataFile(toFileName, report);
    }

    public String[] readDataFromFile(String fromFileName) {
        try (FileReader fileReader = new FileReader(fromFileName)) {
            StringBuilder parsedCsv = new StringBuilder();
            BufferedReader reader = new BufferedReader(fileReader);

            String value;
            if ((value = reader.readLine()) == null) {
                return null;
            }
            do {
                parsedCsv.append(value).append(PARSED_STRING_SEPARATOR);
            } while ((value = reader.readLine()) != null);

            return parsedCsv.toString().split(PARSED_STRING_SEPARATOR);
        } catch (IOException e) {
            throw new RuntimeException("Try to read file was unsuccessful", e);
        }
    }

    private String formReport(String[] parsedString) {
        int buyAmount = 0;
        int supplyAmount = 0;
        StringBuilder result = new StringBuilder();

        for (String data: parsedString) {
            int amountNumber = Integer.parseInt(data.substring(data.indexOf(',') + 1));
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

    private void createNewDataFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Try to write file was unsuccessful", e);
        }
    }
}
