package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int START_VALUE = 0;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] separateDataFromFile = readFromFile(fromFileName);
        String reportValues = calculateReport(separateDataFromFile);
        writeInfoToFile(reportValues, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(COMMA);
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(COMMA);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
    }

    private String calculateReport(String[] dataLines) {
        int supplyValue = START_VALUE;
        int buyValue = START_VALUE;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dataLines.length; i++) {
            if (dataLines[i].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(dataLines[i + 1]);
            } else if (dataLines[i].equals(BUY)) {
                buyValue += Integer.parseInt(dataLines[i + 1]);
            }
        }
        stringBuilder.append(SUPPLY).append(COMMA).append(supplyValue)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyValue)
                .append(System.lineSeparator()).append(RESULT).append(COMMA)
                .append(supplyValue - buyValue);
        return stringBuilder.toString();
    }

    private void writeInfoToFile(String calculatedReport, String toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(calculatedReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write down to the file " + toFile, e);
        }
    }
}
