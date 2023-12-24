package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String[] tableFromFile = readFile(fromFileName);
        for (String line : tableFromFile) {
            if (line.contains("supply")) {
                supply += Integer.parseInt(line.substring(7));
            } else if (line.contains("buy")) {
                buy += Integer.parseInt(line.substring(4));
            }
        }
        String report = createReport(supply, buy);
        writeToFile(report, toFileName);
    }

    public String[] readFile(String fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            String[] fileTable = stringBuilder.toString().split(System.lineSeparator());
            return fileTable;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
    }

    public String createReport(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);
        return stringBuilder.toString();
    }

    public void writeToFile(String text, String toFileName) {
        try {
            File file = new File(toFileName);
            Files.write(file.toPath(), text.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
