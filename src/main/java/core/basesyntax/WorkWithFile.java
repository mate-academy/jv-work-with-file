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
    int buy;
    int supply;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data, supply, buy);
        writeToFile(report, toFileName);
    }

    public String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            try (InputStream inputStream = new FileInputStream(fileName);
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(inputStream))) {
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    if (line.contains("supply")) {
                        supply += Integer.parseInt(line.replaceAll("[a-zA-Z\\p{Punct}]", ""));
                    }else {
                        buy += Integer.parseInt(line.replaceAll("[a-zA-Z\\p{Punct}]", ""));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).toString();
    }

    public String generateReport(String data, int supply, int buy) {
        int difference =  supply - buy;
        return data + System.lineSeparator() + "result" + "," + difference;
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
