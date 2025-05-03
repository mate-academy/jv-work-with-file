package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFileName) {
        try {
            BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder dataFromFileBuilder = new StringBuilder();
            String value = fromFileReader.readLine();
            while (value != null) {
                dataFromFileBuilder.append(value).append(System.lineSeparator());
                value = fromFileReader.readLine();
            }
            return dataFromFileBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file" + fromFileName, e);
        }
    }

    public String generateReport(String data) {
        String[] rows = data.split(System.lineSeparator());
        int buyCount = 0;
        int supplyCount = 0;
        for (String row : rows) {
            String[] splitedRow = row.split(SEPARATOR);
            String operationType = splitedRow[OPERATION_INDEX];
            int quantity = Integer.parseInt(splitedRow[QUANTITY_INDEX]);
            if (BUY.equals(operationType)) {
                buyCount += quantity;
            } else if (SUPPLY.equals(operationType)) {
                supplyCount += quantity;
            }
        }
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR)
                .append(supplyCount).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR)
                .append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supplyCount - buyCount);
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + toFileName, e);
        }
    }
}
