package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_COLUMN = 0;
    private static final int AMOUNT_COLUMN = 1;
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";
    private static final String RESULT_FIELD = "result";

    public WorkWithFile() {
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> data = readData(fromFileName);
        String report = createReport(data);
        writeData(toFileName, report);
    }

    private Map<String, Integer> readData(String fromFileName) {
        Map<String, Integer> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(COMMA_SEPARATOR);
                String type = record[OPERATION_COLUMN];
                int amount = Integer.parseInt(record[AMOUNT_COLUMN]);
                data.merge(type, amount, Integer::sum);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return data;
    }

    private String createReport(Map<String, Integer> data) {
        int supply = data.get(SUPPLY_FIELD);
        int buy = data.get(BUY_FIELD);
        int result = supply - buy;
        return SUPPLY_FIELD + COMMA_SEPARATOR + supply
                + System.lineSeparator()
                + BUY_FIELD + COMMA_SEPARATOR + buy
                + System.lineSeparator()
                + RESULT_FIELD + COMMA_SEPARATOR + result
                + System.lineSeparator();
    }

    private void writeData(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can write data in the file " + toFileName, e);
        }
    }
}
