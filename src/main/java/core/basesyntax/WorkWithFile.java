package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final Character SEPARATOR = ',';

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
            if (operationInfo[0].equals("supply")) {
                supply += Integer.parseInt(operationInfo[1]);
            } else {
                buy += Integer.parseInt(operationInfo[1]);
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buy).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file: " + toFileName, e);
        }
    }
}
