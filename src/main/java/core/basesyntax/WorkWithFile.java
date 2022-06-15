package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String statistics = createReport(data);
        writeToFile(toFileName, statistics);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        }
    }

    private String createReport(String data) {
        int supplySum = 0;
        int buySum = 0;
        String[] splittedData = data.split(System.lineSeparator());
        for (String line : splittedData) {
            String[] splittedLine = line.split(",");
            if (splittedLine[OPERATION_TYPE_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            } else {
                buySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder report = new StringBuilder();
        return report.append("supply").append(",").append(supplySum).append(System.lineSeparator())
                .append("buy").append(",").append(buySum).append(System.lineSeparator())
                .append("result").append(",").append(result).toString();
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
