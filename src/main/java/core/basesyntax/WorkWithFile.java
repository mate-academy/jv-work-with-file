package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";
    private static final int CATEGORY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String PAIR_SEPARATOR = ":";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readDataFromFile(fromFileName);
        String report = createReport(readData);
        writeDataToFile(report, toFileName);
    }

    private static String readDataFromFile(String fromFileName) {
        StringBuilder readData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while (reader.ready()) {
                readData.append(reader.readLine()).append(PAIR_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("File can't be read", e);
        }
        return readData.toString();
    }

    private void writeDataToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toCharArray());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }

    private String createReport(String data) {
        int buyValue = 0;
        int supplyValue = 0;
        for (String pair : data.split(PAIR_SEPARATOR)) {
            String[] keyValue = pair.split(COMMA);
            if (keyValue[CATEGORY_INDEX].equals(SUPPLY_NAME)) {
                supplyValue += Integer.parseInt(keyValue[VALUE_INDEX]);
            } else {
                buyValue += Integer.parseInt(keyValue[VALUE_INDEX]);
            }
        }
        int resultValue = supplyValue - buyValue;
        return SUPPLY_NAME + COMMA + supplyValue + System.lineSeparator()
                + BUY_NAME + COMMA + buyValue + System.lineSeparator()
                + RESULT_NAME + COMMA + resultValue;
    }
}
