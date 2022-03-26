package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private String readData;
    private String report;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        createReport();
        writeToFile(toFileName);
    }

    private void readFromFile(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String data = bufferedReader.readLine();
            while (data != null) {
                stringBuilder.append(data).append(System.lineSeparator());
                data = bufferedReader.readLine();
            }
            readData = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }

    private void createReport() {
        int supply = 0;
        int buy = 0;
        String[] info = readData.split(System.lineSeparator());
        for (int i = 0; i < info.length; i++) {
            if (info[i].startsWith("s")) {
                String[] supplied = info[i].split(",");
                supply += Integer.parseInt(supplied[1]);
            } else {
                String[] bought = info[i].split(",");
                buy += Integer.parseInt(bought[1]);
            }
        }
        StringBuilder stringReport = new StringBuilder();
        stringReport.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        report = stringReport.toString();
    }

    private void writeToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
