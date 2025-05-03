package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        String reportLines = processLines(lines);
        writeFile(reportLines, toFileName);
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

    private String processLines(List<String> lines) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : lines) {
            String[] parts = line.split(COMMA);
            String operations = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operations.equals(SUPPLY)) {
                totalSupply += amount;
            } else {
                totalBuy += amount;
            }
        }

        int result = totalSupply - totalBuy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA)
                .append(totalSupply).append(System.lineSeparator());
        reportBuilder.append(BUY).append(COMMA).append(totalBuy).append(System.lineSeparator());
        reportBuilder.append(RESULT).append(COMMA).append(result);
        return reportBuilder.toString();
    }

    private void writeFile(String lines, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(lines);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
