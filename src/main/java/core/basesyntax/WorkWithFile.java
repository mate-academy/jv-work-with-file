package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileData = readDataFromFile(fromFileName);

        String report = createReport(fileData);

        writeDataToFile(toFileName, report.getBytes());
    }

    private List<String> readDataFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
    }

    private String createReport(List<String> fileData) {
        int supply = 0;
        int buy = 0;

        for (String line:fileData) {
            if ("supply".equals(line.split(",")[0])) {
                supply += Integer.parseInt(line.split(",")[1]);
            } else if ("buy".equals(line.split(",")[0])) {
                buy += Integer.parseInt(line.split(",")[1]);
            }
        }

        return new StringBuilder().append(String.format("%s,%d", "supply", supply))
                .append(System.lineSeparator())
                .append(String.format("%s,%d", "buy", buy))
                .append(System.lineSeparator())
                .append(String.format("%s,%d", "result", supply - buy))
                .append(System.lineSeparator())
                .toString();
    }

    private void writeDataToFile(String toFileName, byte[] writeData) {
        try {
            Files.write(Paths.get(toFileName), writeData);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file: " + toFileName, e);
        }
    }
}
