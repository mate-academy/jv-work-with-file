package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String ROW_SEPARATOR = " ";
    private static final String DATA_FIELDS_SEPARATOR = ",";
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";
    private static final String RESULT_FIELD = "result";
    private static final int FIELD_NAME_INDEX = 0;
    private static final int FIELD_VALUE_INDEX = 1;
    private static StringBuilder BUILDER = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readData = readData(fromFileName);
        String report = makeReport(readData);
        writeData(report, toFileName);
    }

    public String[] readData(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            BUILDER = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                BUILDER.append(value).append(ROW_SEPARATOR);
                value = reader.readLine();
            }
            return BUILDER.toString().split(ROW_SEPARATOR);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    public String makeReport(String[] readData) {
        int supplyValue = 0;
        int buyValue = 0;
        for (String row : readData) {
            String[] splitRow = row.split(DATA_FIELDS_SEPARATOR);
            switch (splitRow[FIELD_NAME_INDEX]) {
                case SUPPLY_FIELD:
                    supplyValue += Integer.parseInt(splitRow[FIELD_VALUE_INDEX]);
                    break;
                case BUY_FIELD:
                    buyValue += Integer.parseInt(splitRow[FIELD_VALUE_INDEX]);
                    break;
                default:
                    break;
            }
        }
        BUILDER = new StringBuilder();
        BUILDER.append(SUPPLY_FIELD).append(DATA_FIELDS_SEPARATOR).append(supplyValue)
                .append(System.lineSeparator())
                .append(BUY_FIELD).append(DATA_FIELDS_SEPARATOR).append(buyValue)
                .append(System.lineSeparator())
                .append(RESULT_FIELD).append(DATA_FIELDS_SEPARATOR).append(supplyValue - buyValue);
        return BUILDER.toString();
    }

    public void writeData(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
