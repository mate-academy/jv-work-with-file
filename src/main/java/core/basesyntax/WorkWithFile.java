package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        final int linesNumber = countLines(fromFileName);

        StatisticData[] operationData = new StatisticData[linesNumber];
        operationData = readFromFile(fromFileName);

        int supply = 0;
        int buy = 0;
        int result;

        for (int i = 0; i < linesNumber; i++) {
            if (operationData[i].getOperationType().equals("supply")) {
                supply += operationData[i].getValue();
            } else {
                buy += operationData[i].getValue();
            }
        }

        result = supply - buy;

        writeToFile(supply, buy, result, toFileName);
    }

    private static StatisticData[] readFromFile(String fileName) {

        final int columnsNumber = 2;
        final int linesNumber = countLines(fileName);

        File file = new File(fileName);
        StatisticData[] data = new StatisticData[linesNumber];

        String[] lines = new String[columnsNumber];
        int dataCounter = 0;

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            String value = buffReader.readLine();

            while (value != null) {
                lines = value.split(",");
                data[dataCounter] = new StatisticData(lines[0], Integer.parseInt(lines[1]));
                dataCounter++;
                value = buffReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot open file", e);
        } catch (IOException e) {
            throw new RuntimeException("IO exception", e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Null pointer exception");
        }
        return data;
    }

    private void writeToFile(int supply, int buy, int result, String fileName) {
        File file = new File(fileName);
        StringBuilder report = new StringBuilder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            report.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                    .append(buy).append(System.lineSeparator()).append("result,").append(result);
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("IO exception", e);
        }
    }

    private static int countLines(String fileName) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot open file", e);
        }
        return lines;
    }

}
