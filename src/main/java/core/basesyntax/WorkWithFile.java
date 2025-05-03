package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_NAMES = 0;
    private static final int INDEX_OF_VALUES = 1;
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readFromFile(fromFileName);
        int[] values = countValues(textFromFile);
        String report = createReport(values);
        saveToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                data.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file - " + fileName, e);
        }
        return data.toString();
    }

    private int[] countValues(String file) {
        String[] linesArray = file.split(System.lineSeparator());
        int totalSupplyValue = 0;
        int totalBuyValue = 0;
        for (String line : linesArray) {
            String[] data = line.split(COMMA_SEPARATOR);
            if (data[INDEX_OF_NAMES].equals("supply")) {
                totalSupplyValue += Integer.parseInt(data[INDEX_OF_VALUES]);
            } else {
                totalBuyValue += Integer.parseInt(data[INDEX_OF_VALUES]);
            }
        }
        return new int[]{totalSupplyValue, totalBuyValue};
    }

    private String createReport(int[] data) {
        return "supply," + data[0] + System.lineSeparator()
                + "buy," + data[1] + System.lineSeparator()
                + "result," + (data[0] - data[1]);
    }

    private void saveToFile(String toFile, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't save report to file - " + toFile, e);
        }
    }
}
