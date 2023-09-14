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
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMETER_COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeReport(toFileName, generationReport(readFromFile(fromFileName)));
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

    private String generationReport(String fileData) {
        StringBuilder report = new StringBuilder();
        String[] allData = fileData.split(LINE_SEPARATOR);
        int totalSupply = 0;
        int totalBuy = 0;
        for (String data : allData) {
            String[] operationData = data.split(DELIMETER_COMA);
            String operationType = operationData[0];
            String operationValue = operationData[1];
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

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
