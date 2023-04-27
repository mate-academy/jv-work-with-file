package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int INDEX_OF_SUPPLY_AMOUNT = 0;
    private static final int INDEX_OF_BUY_AMOUNT = 1;
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY_TYPE_STRING = "supply";
    private static final String BUY_TYPE_STRING = "buy";
    private static final String RESULT_REPORT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readDataFromFile(fromFileName);
        String report = createReportFromData(data);
        writeReportIntoFile(report, toFileName);
    }

    private void writeReportIntoFile(String report, String toFileName) {
        File file = new File(toFileName);
        deleteFileIfExists(file, toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report into file", e);
        }
    }

    private void deleteFileIfExists(File file, String toFileName) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Error deleting file", e);
        }
    }

    private String createReportFromData(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyAmount = data[INDEX_OF_SUPPLY_AMOUNT];
        int buyAmount = data[INDEX_OF_BUY_AMOUNT];
        int result = supplyAmount - buyAmount;
        stringBuilder.append(SUPPLY_TYPE_STRING).append(COMMA_SEPARATOR)
                .append(supplyAmount).append(System.lineSeparator());
        stringBuilder.append(BUY_TYPE_STRING).append(COMMA_SEPARATOR)
                .append(buyAmount).append(System.lineSeparator());
        stringBuilder.append(RESULT_REPORT_STRING).append(COMMA_SEPARATOR)
                .append(result);
        return stringBuilder.toString();
    }

    private int[] readDataFromFile(String fromFileName) {
        int[] data = new int[2];
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                parseDataLine(data, line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file", e);
        }
        return data;
    }

    private void parseDataLine(int[] data, String line) {
        String[] lineData = line.split(COMMA_SEPARATOR);
        String operationType = lineData[INDEX_OF_OPERATION_TYPE];
        int amount = Integer.valueOf(lineData[INDEX_OF_AMOUNT]);
        if (operationType.equals(SUPPLY_TYPE_STRING)) {
            data[INDEX_OF_SUPPLY_AMOUNT] += amount;
        } else if (operationType.equals(BUY_TYPE_STRING)) {
            data[INDEX_OF_BUY_AMOUNT] += amount;
        }
    }
}
