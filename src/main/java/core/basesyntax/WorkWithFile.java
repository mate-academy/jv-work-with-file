package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private int[] readFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(DELIMITER);
                if (data[OPERATION_INDEX].equals("supply")) {
                    supply += Integer.parseInt(data[AMOUNT_INDEX]);
                } else if (data[OPERATION_INDEX].equals("buy")) {
                    buy += Integer.parseInt(data[AMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file" + fromFileName, e);
        }
        return new int[] {supply, buy};
    }

    private String createReport(int[] data) {
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(data[SUPPLY_INDEX]).append(System.lineSeparator())
                .append("buy,").append(data[BUY_INDEX]).append(System.lineSeparator())
                .append("result,").append(data[SUPPLY_INDEX] - data[BUY_INDEX]);
        return result.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + toFileName, e);
        }
    }
}

