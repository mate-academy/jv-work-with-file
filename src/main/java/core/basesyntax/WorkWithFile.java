package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFileMethod(fromFileName);
        String report = getReport(data);
        writeDataToFileMethod(report, toFileName);
    }

    private String readFileMethod(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(SEPARATOR);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file: " + fileName, e);
        }
        return builder.toString();
    }

    private String getReport(String file) {
        String firstItem = "supply";
        String secondItem = "buy";
        int totalSupply = 0;
        int totalBuy = 0;
        int result = 0;
        String[] dataFromFile = file.split(SEPARATOR);
        for (int i = 0; i < dataFromFile.length; i++) {
            if (dataFromFile[i].equals(firstItem)) {
                totalSupply += Integer.parseInt(dataFromFile[i + 1]);
            }
            if (dataFromFile[i].equals(secondItem)) {
                totalBuy += Integer.parseInt(dataFromFile[i + 1]);
            }
            result = totalSupply - totalBuy;
        }

        return firstItem + SEPARATOR + totalSupply + System.lineSeparator()
                + secondItem + SEPARATOR + totalBuy + System.lineSeparator()
                + "result" + SEPARATOR + result;
    }

    private void writeDataToFileMethod(String reports, String fileName) {
        try (BufferedWriter writeFile = new BufferedWriter(new FileWriter(fileName))) {
            writeFile.write(reports);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file: " + fileName, e);
        }
    }
}
