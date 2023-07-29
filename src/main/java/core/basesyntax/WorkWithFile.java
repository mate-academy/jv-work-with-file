package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String report = makeReport(fileContent);
        writeReportToFile(toFileName, report);
    }

    private String readFileContent(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                content.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't reat this file:" + fromFileName, e);
        }
        return content.toString();
    }

    private String makeReport(String fileContent) {
        int supplySum = 0;
        int buySum = 0;
        StringBuilder report = new StringBuilder();
        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[FIRST_INDEX];
            int amount = Integer.parseInt(parts[SECOND_INDEX].trim());
            if (operation.equals("supply")) {
                supplySum += amount;
            } else if (operation.equals("buy")) {
                buySum += amount;
            }
        }
        int result = supplySum - buySum;
        report.append("supply,")
                .append(supplySum)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buySum).append(System.lineSeparator())
                .append("result,")
                .append(result);
        return report.toString();
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data: " + toFileName, e);
        }
    }
}
