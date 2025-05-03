package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFromFile(fromFileName);
        String[] splitData = readData.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String element : splitData) {
            String[] line = element.split(",");
            if (line[0].equals("supply")) {
                supply += Integer.parseInt(line[1]);
            } else if (line[0].equals("buy")) {
                buy += Integer.parseInt(line[1]);
            }
        }
        int result = supply - buy;
        String reportData = createReport(supply, buy, result);
        writeToFile(toFileName, reportData);
    }

    private String readFromFile(String fromFileName) {
        File importFile = new File(fromFileName);
        StringBuilder importData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(importFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                importData.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return importData.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String createReport(int supply, int buy, int result) {
        StringBuilder reportData = new StringBuilder();
        reportData.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        return reportData.toString();
    }

    private void writeToFile(String toFileName, String reportData) {
        File reportFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
