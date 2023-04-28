package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_REPORT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        Data data = readDataFromFile(fromFileName);
        String report = createReportFromData(data);
        deleteFileIfExists(toFileName);
        writeReportIntoFile(report, toFileName);
    }

    private void writeReportIntoFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report into file", e);
        }
    }

    private void deleteFileIfExists(String toFileName) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Error deleting file", e);
        }
    }

    private String createReportFromData(Data data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyAmount = data.getSupplyAmount();
        int buyAmount = data.getBuyAmount();
        int result = supplyAmount - buyAmount;
        stringBuilder
                .append(SUPPLY_OPERATION).append(COMMA_SEPARATOR).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(COMMA_SEPARATOR).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT_REPORT).append(COMMA_SEPARATOR).append(result);
        return stringBuilder.toString();
    }

    private Data readDataFromFile(String fromFileName) {
        Data data = new Data();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                parseDataLine(data, line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file", e);
        }
        return data;
    }

    private void parseDataLine(Data data, String line) {
        String[] lineData = line.split(COMMA_SEPARATOR);
        String operationType = lineData[INDEX_OF_OPERATION_TYPE];
        int amount = Integer.valueOf(lineData[INDEX_OF_AMOUNT]);
        if (operationType.equals(SUPPLY_OPERATION)) {
            data.setSupplyAmount(data.getSupplyAmount() + amount);
        } else if (operationType.equals(BUY_OPERATION)) {
            data.setBuyAmount(data.getBuyAmount() + amount);
        }
    }
}
