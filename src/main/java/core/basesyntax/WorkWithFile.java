package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToCsv(createReport(readFromCsv(fromFileName)), toFileName);
    }

    private String readFromCsv(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String content = bufferedReader.readLine();
            while (content != null) {
                stringBuilder.append(content).append(",");
                content = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);

        }
        return stringBuilder.toString();
    }

    private String createReport(String content) {
        int buy = 0;
        int supply = 0;
        String[] report = content.split(",");
        for (int i = 0; i < report.length; i = i + 2) {
            if (report[i].equals("buy")) {
                buy = buy + Integer.parseInt(report[i + 1]);
            } else if (report[i].equals("supply")) {
                supply = supply + Integer.parseInt(report[i + 1]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator() + "buy," + buy
                + System.lineSeparator() + "result," + result;
    }

    private void writeToCsv(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Could`t write a file", e);
        }
    }
}
