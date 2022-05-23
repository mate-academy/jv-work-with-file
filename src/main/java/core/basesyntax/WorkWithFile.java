package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeReport(toFileName, report);
    }

    private String createReport(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int totalSupply = 0;
            int totalBuy = 0;
            String line = reader.readLine();

            while (line != null) {
                String[] info = line.split(",");
                if (info[0].equals("supply")) {
                    totalSupply += Integer.parseInt(info[1]);
                } else if (info[0].equals("buy")) {
                    totalBuy += Integer.parseInt(info[1]);
                }
                line = reader.readLine();
            }
            return "supply," + totalSupply + System.lineSeparator()
                    + "buy," + totalBuy + System.lineSeparator()
                    + "result," + (totalSupply - totalBuy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
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
