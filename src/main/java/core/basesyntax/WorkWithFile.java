package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        List<String> reportLines = processLines(lines);
        writeFile(reportLines, toFileName);
    }

    public void writeFile(List<String> lines, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }

    public List<String> processLines(List<String> lines) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            String operations = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operations.equals("supply")) {
                totalSupply += amount;
            } else {
                totalBuy += amount;
            }
        }

        int result = totalSupply - totalBuy;
        List<String> reportLines = new ArrayList<>();
        reportLines.add("supply," + totalSupply);
        reportLines.add("buy," + totalBuy);
        reportLines.add("result," + result);

        return reportLines;
    }

    public List<String> readFile(String fromFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                lines.add(value);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return lines;
    }
}
