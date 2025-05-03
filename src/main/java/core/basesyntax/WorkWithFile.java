package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData(fromFileName);
        String report = createReport(data);
        writeReport(toFileName, report);
    }

    private String readData(String fromFileName) {
        StringBuilder dataBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from the file " + fromFileName, e);
        }
        return dataBuilder.toString();
    }

    private String createReport(String data) {
        int supplySum = 0;
        int buySum = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] elements = line.split(COMMA);
            String operationType = elements[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(elements[AMOUNT_INDEX]);

            if (operationType.equals(SUPPLY)) {
                supplySum += amount;
            } else if (operationType.equals(BUY)) {
                buySum += amount;
            }
        }

        int result = supplySum - buySum;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).append(System.lineSeparator());

        return reportBuilder.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to the file " + toFileName, e);
        }
    }
}
