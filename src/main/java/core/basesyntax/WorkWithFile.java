package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String result = createReport(calculateValue(dataFromFile));
        writeToFile(toFileName, result);

    }

    private String[] readFromFile(String fileName) {
        StringBuilder someData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int value = reader.read();
            while (value != -1) {
                someData.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file");
        }
        return someData.toString().split("\\R");
    }

    private int[] calculateValue(String[] arr) {
        int buyNumber = 0;
        int supplyNumber = 0;
        for (int i = 0; i < arr.length; i++) {
            String[] data = arr[i].split(",");
            if (data[0].equals("buy")) {
                buyNumber += Integer.parseInt(data[1]);
            } else {
                supplyNumber += Integer.parseInt(data[1]);
            }
        }
        return new int[] {supplyNumber,buyNumber};
    }

    private String createReport(int[] arr) {
        StringBuilder report = new StringBuilder();
        report.append("supply,")
                .append(arr[0])
                .append(System.lineSeparator())
                .append("buy,")
                .append(arr[1])
                .append(System.lineSeparator())
                .append("result,")
                .append(arr[0] - arr[1]);
        return report.toString();
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file");
        }
    }
}
