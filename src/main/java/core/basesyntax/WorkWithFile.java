package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class WorkWithFile {
    private final StringBuilder lineRead = new StringBuilder();
    private final StringBuilder report = new StringBuilder();

    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName).split(",");
        String report = createReport(getResult(data));
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineRead.append(line).append(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read all line", e);
        }
        return lineRead.toString();
    }

    public int getResult(String[] data) {
        for (int i = 0, j = 1; i < data.length; i += 2, j += 2) {
            if (data[i].equals("supply")) {
                supply += Integer.parseInt(data[j]);
            }
            if (data[i].equals("buy")) {
                buy += Integer.parseInt(data[j]);
            }
        }
        return supply - buy;
    }

    public String createReport(int result) {
        String nextLine = System.lineSeparator();
        report.append("supply,")
                .append(supply)
                .append(nextLine)
                .append("buy,")
                .append(buy)
                .append(nextLine)
                .append("result,")
                .append(result);
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), Collections.singleton(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write text in file", e);
        }
    }
}
