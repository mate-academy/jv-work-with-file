package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SEPARATOR = ",";
    public static final String NEW_LINE = System.lineSeparator();
    public static final String RESULT_HEADER = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String fileContent = readFileContent(reader);
            String report = formReport(fileContent);
            writeReportToFile(writer, report);

        } catch (IOException e) {
            throw new RuntimeException("Error reading from or writing to file", e);
        }
    }

    private String readFileContent(BufferedReader reader) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            contentBuilder.append(line).append(NEW_LINE);
        }
        return contentBuilder.toString().trim();
    }

    private String formReport(String fileContent) {
        int supplyTotal = 0;
        int buyTotal = 0;
        StringBuilder reportBuilder = new StringBuilder();

        String[] lines = fileContent.split(NEW_LINE);
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            if (parts.length == 2) {
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if ("supply".equals(operation)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operation)) {
                    buyTotal += amount;
                }
            }
        }

        int result = supplyTotal - buyTotal;
        reportBuilder.append("supply").append(SEPARATOR).append(supplyTotal).append(NEW_LINE)
                .append("buy").append(SEPARATOR).append(buyTotal).append(NEW_LINE)
                .append(RESULT_HEADER).append(SEPARATOR).append(result);
        return reportBuilder.toString();
    }

    private void writeReportToFile(BufferedWriter writer, String report) throws IOException {
        writer.write(report);
    }
}
