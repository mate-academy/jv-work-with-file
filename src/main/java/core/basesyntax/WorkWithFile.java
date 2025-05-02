package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA_DELIMITER = ",";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_RESULT = "result";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_VALUE = 1;
    private List<List<String>> records = new ArrayList<>();
    private StringBuilder report = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        getRecordsFromFile(fromFileName);
        createReport();
        insertReportToFile(toFileName);
    }

    private void getRecordsFromFile(String file) {
        records.clear();
        report.setLength(0);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] row = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(row));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        }
    }

    private void createReport() {
        int supplySum = 0;
        int buySum = 0;
        for (List<String> row : records) {
            if (row.get(INDEX_OF_OPERATION).equals(OPERATION_BUY)) {
                buySum += Integer.parseInt(row.get(INDEX_OF_VALUE));
            } else if (row.get(INDEX_OF_OPERATION).equals(OPERATION_SUPPLY)) {
                supplySum += Integer.parseInt(row.get(INDEX_OF_VALUE));
            }
        }

        report.append(OPERATION_SUPPLY).append(COMMA_DELIMITER).append(supplySum)
                .append(System.lineSeparator())
                .append(OPERATION_BUY).append(COMMA_DELIMITER).append(buySum)
                .append(System.lineSeparator())
                .append(OPERATION_RESULT).append(COMMA_DELIMITER).append(supplySum - buySum)
                .append(System.lineSeparator());
    }

    private void insertReportToFile(String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        }
    }
}
