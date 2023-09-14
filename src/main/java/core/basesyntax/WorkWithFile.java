package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";
    private static final int INDEX_OF_TYPE = 0;
    private static final int INDEX_OF_VALUE = 1;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMETER_COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder fileData = new StringBuilder();
        try {
            fileData.append(Files.readString(Path.of(fromFileName))).append(LINE_SEPARATOR);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return fileData.toString();
    }

    private String generateReport(String data) {
        StringBuilder report = new StringBuilder();
        String[] allData = data.split(LINE_SEPARATOR);
        int totalSupply = 0;
        int totalBuy = 0;
        for (String curretData : allData) {
            String[] operationData = curretData.split(DELIMETER_COMA);
            String operationType = operationData[INDEX_OF_TYPE];
            String operationValue = operationData[INDEX_OF_VALUE];
            if (operationType.equals(SUPPLY_NAME)) {
                totalSupply += Integer.parseInt(operationValue);
            } else {
                totalBuy += Integer.parseInt(operationValue);
            }
        }
        int result = totalSupply - totalBuy;
        return report.append(SUPPLY_NAME).append(DELIMETER_COMA)
                .append(totalSupply).append(LINE_SEPARATOR)
                .append(BUY_NAME).append(DELIMETER_COMA)
                .append(totalBuy).append(LINE_SEPARATOR)
                .append(RESULT_NAME).append(DELIMETER_COMA).append(result)
                .toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
