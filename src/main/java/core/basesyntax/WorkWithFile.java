package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder firstFileData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String value = br.readLine();
            while (value != null) {
                firstFileData.append(value).append("|");
                value = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return firstFileData.toString();
    }

    private String createReport(String dataFromFile) {
        StringBuilder report = new StringBuilder();
        int supply = 0;
        int buy = 0;
        for (String str : dataFromFile.split("\\|")) {
            String[] a = str.split(",");
            if (a[0].equals("buy")) {
                buy += Integer.parseInt(a[1]);
            } else {
                supply += Integer.parseInt(a[1]);
            }
        }
        report.append("supply,").append(supply)
                .append(System.lineSeparator())
                .append("buy,").append(buy)
                .append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
