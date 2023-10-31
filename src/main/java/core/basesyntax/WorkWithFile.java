package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String currentLine = readFile(fromFileName);
        String report = createReport(currentLine);
        fileWrite(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File inputfile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputfile))) {
            StringBuilder fileContent = new StringBuilder();
            String currentLine = reader.readLine();
            while (currentLine != null) {
                fileContent.append(currentLine).append(COMMA);
                currentLine = reader.readLine();
            }
            return fileContent.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String createReport(String currentLine) {
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;
        String[] data = currentLine.split(COMMA);
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supplyResult += Integer.parseInt(data[i + 1]);
            } else if (data[i].equals("buy")) {
                buyResult += Integer.parseInt(data[i + 1]);
            }
            result = supplyResult - buyResult;
        }
        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder report = resultBuilder.append("supply").append(COMMA).append(supplyResult)
                .append(System.lineSeparator())
                .append("buy").append(COMMA).append(buyResult)
                .append(System.lineSeparator())
                .append("result").append(COMMA).append(result);
        return report.toString();
    }

    private void fileWrite(String toFileName, String report) {
        File outputfile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}

