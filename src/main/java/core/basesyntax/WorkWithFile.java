package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int DATA_INDEX = 1;
    private static final String SUPPLY_LINE = "supply,";
    private static final String BUY_LINE = "buy,";
    private static final String RESULT_LINE = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fileNameToRead) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameToRead))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        int supplySum = 0;
        int buySum = 0;
        int result;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[OPERATION_TYPE_INDEX].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(parts[DATA_INDEX]);
            } else {
                buySum += Integer.parseInt(parts[DATA_INDEX]);
            }
        }
        result = supplySum - buySum;
        return SUPPLY_LINE + supplySum + System.lineSeparator()
                + BUY_LINE + buySum + System.lineSeparator()
                + RESULT_LINE + result;
    }

    private void writeReportToFile(String report, String fileNameToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameToWrite))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write the file", e);
        }
    }
}
