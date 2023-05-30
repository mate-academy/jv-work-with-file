package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_DATA = 1;
    private static final String DELIMITER = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String OPERATION_EXPENSE = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputInfo = readFile(fromFileName);
        String reportText = generateReport(inputInfo);
        writeToFile(reportText, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while ((value != - 1)) {
                content.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return content.toString();
    }

    private String generateReport(String inputInfo) {
        StringBuilder resultReport = new StringBuilder();
        String[] inputArr = inputInfo.split(System.lineSeparator());
        int countSupply = 0;
        int countBuy = 0;
        for (String lines : inputArr) {
            if (lines.split(DELIMITER)[INDEX_OPERATION].equals(SUPPLY_OPERATION)) {
                countSupply = countSupply
                        + Integer.parseInt(lines.split(DELIMITER)[INDEX_DATA]);
            } else {
                countBuy = countBuy
                        + Integer.parseInt(lines.split(DELIMITER)[INDEX_DATA]);
            }
        }
        return (resultReport.append(SUPPLY_OPERATION).append(DELIMITER)
                .append(countSupply).append(System.lineSeparator())
                .append(OPERATION_EXPENSE).append(DELIMITER).append((countBuy))
                .append(System.lineSeparator())
                .append("result").append(DELIMITER).append(countSupply - countBuy)
                .append(System.lineSeparator()).toString());
    }

    private void writeToFile(String reportText, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportText);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
