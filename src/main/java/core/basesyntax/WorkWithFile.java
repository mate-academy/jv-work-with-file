package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String ACTION_SUPPLY = "supply";
    private static final String ACTION_BUY = "buy";
    private static final int ACTION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(processData(readFromFile(fromFileName)), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        builder.delete(0, builder.length());
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));) {
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(System.lineSeparator());
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file "
                    + fromFileName + System.lineSeparator() + e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String processData(String[] report) {
        int supplyValue = 0;
        int buyValue = 0;
        builder.delete(0, builder.length());
        for (String line : report) {
            String[] actions = line.split(",");
            if (actions[ACTION_INDEX].equals(ACTION_BUY)) {
                buyValue += Integer.parseInt(actions[VALUE_INDEX]);
            } else {
                supplyValue += Integer.parseInt(actions[VALUE_INDEX]);
            }
        }
        return builder.append(ACTION_SUPPLY + ",")
                .append(supplyValue).append(System.lineSeparator())
                .append(ACTION_BUY + ",").append(buyValue).append(System.lineSeparator())
                .append("result,").append(supplyValue - buyValue).toString();
    }

    private void writeToFile(String data, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + file + System.lineSeparator() + e);
        }
    }
}
