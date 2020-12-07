package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        String report = createReport();
        writeToFile(report, toFileName);
    }

    private void readFromFile(String fileName) {
        File file = new File(fileName);
        String newLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((newLine = bufferedReader.readLine()) != null) {
                String[] lineWords = newLine.split(",");
                if (lineWords[0].equals("supply")) {
                    supply += Integer.parseInt(lineWords[1]);
                } else {
                    buy += Integer.parseInt(lineWords[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
    }

    private String createReport() {
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(supply - buy);
        return report.toString();
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file: " + fileName, e);
        }
    }
}
