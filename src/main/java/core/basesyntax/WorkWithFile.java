package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static String supply = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = getReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String value = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String data = stringBuilder.toString();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file" + fromFileName, e);
        }
    }

    private String getReport(String data) {
        int supplyValue = 0;
        int buyValue = 0;
        int result = 0;
        String[] dataFromFile = data.split(System.lineSeparator());
        for (int i = 0; i < dataFromFile.length; i++) {
            String[] operation = dataFromFile[i].split(",");
            int secondPart = parseInt(operation[VALUE_INDEX]);
            if (operation[OPERATION_INDEX].equals(supply)) {
                supplyValue += secondPart;
            } else {
                buyValue += secondPart;
            }
        }
        result = supplyValue - buyValue;
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplyValue).append(System.lineSeparator())
                .append("buy,").append(buyValue).append(System.lineSeparator())
                .append("result,").append(result);
        return report.toString();
    }

    public void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a file" + toFileName);
        }
    }
}
