package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final Character SEPARATOR = ',';
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String file = readFile(fromFileName);
        String result = generateReport(file);
        writeToFile(toFileName, result);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String generateReport(String fileData) {
        int buy = 0;
        int supply = 0;
        String[] dataLines = fileData.split(System.lineSeparator());
        for (String line : dataLines) {
            String[] operationInfo = line.split(String.valueOf(SEPARATOR));
            final String value = operationInfo[1];
            if (operationInfo[0].equals(SUPPLY)) {
                supply += Integer.parseInt(value);
            } else {
                buy += Integer.parseInt(value);
            }
        }
        int result = supply - buy;
        return new StringBuilder(SUPPLY).append(SEPARATOR).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file: " + toFileName, e);
        }
    }
}
