package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        StringBuilder reportBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                if ("buy".equals(data[0])) {
                    buy += Integer.parseInt(data[1]);
                } else if ("supply".equals(data[0])) {
                    supply += Integer.parseInt(data[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file.", e);
        }
        int result = supply - buy;
        reportBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
        String report = reportBuilder.toString();
        File resultFile = new File(toFileName);
        try {
            Files.write(resultFile.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write file.", e);
        }
    }
}
