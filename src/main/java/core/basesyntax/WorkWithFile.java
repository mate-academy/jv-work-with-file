package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final String REGEX = "\\W+";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private int[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            int supplyResult = 0;
            int buyResult = 0;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(REGEX);
                switch (data[OPERATION_TYPE_INDEX]) {
                    case SUPPLY:
                        supplyResult += Integer.parseInt(data[AMOUNT_INDEX]);
                        break;
                    case BUY:
                        buyResult += Integer.parseInt(data[AMOUNT_INDEX]);
                        break;
                    default:
                        break;
                }
            }
            return new int[] {supplyResult, buyResult};
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file" + fromFileName, e);
        }
    }

    private String generateReport(int[] data) {
        StringBuilder reportBuilder = new StringBuilder();
        int result = data[SUPPLY_INDEX] - data[BUY_INDEX];
        reportBuilder
                .append(SUPPLY)
                .append(DELIMITER)
                .append(data[SUPPLY_INDEX])
                .append(System.lineSeparator())
                .append(BUY)
                .append(DELIMITER)
                .append(data[BUY_INDEX])
                .append(System.lineSeparator())
                .append(RESULT)
                .append(DELIMITER)
                .append(result)
                .append(System.lineSeparator());
        return reportBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write result to file" + toFileName, e);
        }
    }
}
