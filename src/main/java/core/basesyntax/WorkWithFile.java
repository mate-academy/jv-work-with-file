package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final String DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputData = readFromFile(fromFileName).split(System.lineSeparator());
        String report = createReport(inputData);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String createReport(String[] inputData) {
        int supplyResult = 0;
        int buyResult = 0;
        for (String data : inputData) {
            String[] dataByColumns = data.split(DATA_SEPARATOR);
            if (dataByColumns[OPERATION_INDEX].equalsIgnoreCase(SUPPLY)) {
                supplyResult += Integer.parseInt(dataByColumns[AMMOUNT_INDEX]);
            } else if (dataByColumns[OPERATION_INDEX].equalsIgnoreCase(BUY)) {
                buyResult += Integer.parseInt(dataByColumns[AMMOUNT_INDEX]);
            }
        }

        StringBuilder resultData = new StringBuilder();
        resultData.append(SUPPLY).append(DATA_SEPARATOR).append(supplyResult)
        .append(System.lineSeparator()).append(BUY).append(DATA_SEPARATOR)
        .append(buyResult).append(System.lineSeparator()).append("result")
                .append(DATA_SEPARATOR).append(supplyResult - buyResult);
        return resultData.toString();
    }
}
