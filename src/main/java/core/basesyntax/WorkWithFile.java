package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private int totalSupply = 0;
    private int totalBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        analyzeData(fromFileName);
        String report = createReport(totalSupply, totalBuy);
        writeReport(toFileName, report);
    }

    private void analyzeData(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                analyzeRecord(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void analyzeRecord(String record) {
        String[] info = record.split(",");
        if (info[0].equals("supply")) {
            totalSupply += Integer.parseInt(info[1]);
        } else if (info[0].equals("buy")) {
            totalBuy += Integer.parseInt(info[1]);
        }
    }

    private String createReport(int totalSupply, int totalBuy) {
        return "supply," + totalSupply + System.lineSeparator()
                + "buy," + totalBuy + System.lineSeparator()
                + "result," + (totalSupply - totalBuy);
    }

    private void writeReport(String toFileName, String report) {
        try {
            File statistic = new File(toFileName);
            Files.write(statistic.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
