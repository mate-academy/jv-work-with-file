package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int POSITION_OPERATION_TYPE = 0;
    private static final int POSITION_AMOUNT = 1;
    private static final String SEPARATOR = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeData(createReport(fromFileName), toFileName);
    }

    private String createReport(String fromFileName) {
        int supply = 0;
        int buy = 0;
        String[] lines = readData(fromFileName).split(System.lineSeparator());
        for (String line : lines) {
            String[] data = line.split(SEPARATOR);
            if (data[POSITION_OPERATION_TYPE].equals(OPERATION_SUPPLY)) {
                supply += Integer.parseInt(data[POSITION_AMOUNT]);
            }
            if (data[POSITION_OPERATION_TYPE].equals(OPERATION_BUY)) {
                buy += Integer.parseInt(data[POSITION_AMOUNT]);
            }
        }
        return " supply," + supply + System.lineSeparator() + "buy," + buy
                + System.lineSeparator() + "result," + (supply - buy);
    }

    private String readData(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return builder.toString();
    }

    private void writeData(String data, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
