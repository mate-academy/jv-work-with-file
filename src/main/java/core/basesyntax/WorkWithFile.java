package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read file.", exception);
        }
        return content.toString();
    }

    private String createReport(String data) {
        int totalSupply = 0;
        int totalBuy = 0;
        int firstPart = 0;
        int secondPart = 1;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[firstPart];
            int amount = Integer.parseInt(parts[secondPart]);

            if (operation.equals("supply")) {
                totalSupply += amount;
            } else if (operation.equals("buy")) {
                totalBuy += amount;
            }
        }

        int result = totalSupply - totalBuy;

        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(totalSupply).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(totalBuy).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(result).append(LINE_SEPARATOR)
                .toString();
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file.", exception);
        }
    }
}
