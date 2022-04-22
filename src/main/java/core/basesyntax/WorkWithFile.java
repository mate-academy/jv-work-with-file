package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFromFile(fromFileName);
        String report = calcResult(data);
        writeToFile(report, toFileName);
    }

    private String calcResult(int[] data) {
        StringBuilder reportBuilder;
        reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(data[0]).append(System.lineSeparator())
                .append("buy,").append(data[1]).append(System.lineSeparator())
                .append("result,").append(data[0] - data[1])
                .append(System.lineSeparator());
        return reportBuilder.toString().trim();
    }

    private int[] readFromFile(String fromFileName) {
        int[] data = {0, 0};
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String read = bufferedReader.readLine();
            while (read != null) {
                String[] readSplit = read.split(",");
                read = bufferedReader.readLine();
                if (readSplit[0].equals("supply")) {
                    data[0] += Integer.parseInt(readSplit[1]);
                } else {
                    data[1] += Integer.parseInt(readSplit[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return data;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }
}
