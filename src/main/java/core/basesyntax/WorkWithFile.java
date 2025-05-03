package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFile(fromFileName);
        String report = prepareReport(readData);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                result.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file : " + fromFileName, e);
        }
        return result.toString();
    }

    private String prepareReport(String readFile) {
        StringBuilder report = new StringBuilder();
        String[] reportData = readFile.split("\\W+");
        String value;
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < reportData.length; i = i + 2) {
            value = reportData[i + 1];
            if (reportData[i].equals("supply")) {
                supply += Integer.parseInt(value);
            } else {
                buy += Integer.parseInt(value);
            }
        }

        int result = supply - buy;
        report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to file : " + toFileName, e);
        }
    }
}
