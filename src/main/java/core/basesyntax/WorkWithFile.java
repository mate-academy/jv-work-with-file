package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(report, toFileName);
    }

    public String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            try (InputStream inputStream = new FileInputStream(fileName);
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(inputStream))) {
                while (bufferedReader.ready()) {
                    builder.append(bufferedReader.readLine()).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return builder.toString();
    }

    public String generateReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] forReport = data.split(System.lineSeparator());
        for (String s : forReport) {
            if (s.contains("supply")) {
                supply += Integer.parseInt(s.replaceAll("[a-zA-Z\\p{Punct}]", ""));
            }
            if (s.contains("buy")) {
                buy += Integer.parseInt(s.replaceAll("[a-zA-Z\\p{Punct}]", ""));
            }
        }
        int difference = supply - buy;
        return "supply," + supply + System.lineSeparator() + "buy," +
                buy + System.lineSeparator() + "result," + difference;
    }

    public void writeToFile(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            File file = new File(fileName);
            if (file.length() == 0) {
                bufferedWriter.write(report);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
