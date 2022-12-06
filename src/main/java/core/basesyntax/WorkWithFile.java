package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readDataFromFile(fromFileName);
        String reportData = createReport(dataFromFile);
        writeToFile(reportData, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName,e);
        }

        return stringBuilder.toString();
    }

    private String createReport(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;

        for (String line: data.split(System.lineSeparator())) {
            String operationType = line.split(",")[OPERATION_TYPE_POSITION];
            String amount = line.split(",")[AMOUNT_POSITION];

            if (operationType.equals("supply")) {
                supply += Integer.parseInt(amount);
            } else {
                buy += Integer.parseInt(amount);
            }
        }

        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);

        return stringBuilder.toString();
    }

    private void writeToFile(String reportData, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
