package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String reportData = createReport(data);
        writeToFile(toFileName, reportData);
    }

    private String[] readFromFile(String inputData) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputData))) {
            String lineValue = bufferedReader.readLine();
            while (lineValue != null) {
                stringBuilder.append(lineValue).append(System.lineSeparator());
                lineValue = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + inputData, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] readData) {
        int supplyCount = 0;
        int buyCount = 0;
        for (String data : readData) {
            String[] currentLine = data.split(COMMA_SEPARATOR);
            if (currentLine[0].equals(SUPPLY_STRING)) {
                supplyCount += Integer.parseInt(currentLine[1]);
            } else {
                buyCount += Integer.parseInt(currentLine[1]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder().append(SUPPLY_STRING)
                .append(COMMA_SEPARATOR).append(supplyCount).append(System.lineSeparator())
                .append(BUY_STRING).append(COMMA_SEPARATOR).append(buyCount)
                .append(System.lineSeparator()).append("result").append(COMMA_SEPARATOR)
                .append(supplyCount - buyCount);
        return reportBuilder.toString();
    }

    private void writeToFile(String toFile, String reportData) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFile, e);
        }
    }
}
