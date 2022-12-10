package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int columnsNumber = 2;

    public void getStatistic(String fromFileName, String toFileName) {

        final int linesNumber = countLines(fromFileName);

        String[][] operationData = new String[linesNumber][columnsNumber];
        operationData = readFromFile(fromFileName);

        int supply = 0;
        int buy = 0;
        int result;

        for (int i = 0; i < linesNumber; i++) {
            if (operationData[i][0].equals("supply")) {
                supply += Integer.parseInt(operationData[i][1]);
            } else {
                buy += Integer.parseInt(operationData[i][1]);
            }
        }

        result = supply - buy;

        writeToFile(supply, buy, result, toFileName);
    }

    private static String[][] readFromFile(String fileName) {

        final int linesNumber = countLines(fileName);

        File file = new File(fileName);
        String[][] data = new String[linesNumber][columnsNumber];

        String[] lines = new String[columnsNumber];
        int dataCounter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                lines = value.split(",");
                data[dataCounter][0] = lines[0];
                data[dataCounter][1] = lines[1];
                dataCounter++;
                value = reader.readLine();
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
