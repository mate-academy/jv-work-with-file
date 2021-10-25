package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String finalData = createReport(readDataFromFile(fromFileName));
            writer.write(finalData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file.", e);
        }
    }

    private StringBuilder readDataFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder fileStrings = new StringBuilder();
            String fromFile = reader.readLine();
            while (fromFile != null) {
                fileStrings.append(fromFile)
                        .append(System.lineSeparator());
                fromFile = reader.readLine();
            }
            return fileStrings;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file.", e);
        }
    }

    private String createReport(StringBuilder fileStrings) {
        StringBuilder report = new StringBuilder();
        String[] splittedLines = fileStrings.toString().split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String currentLine : splittedLines) {
            String[] splittedCurrentLine = currentLine.split(",");
            switch (splittedCurrentLine[0]) {
                case "buy":
                    buy += Integer.parseInt(splittedCurrentLine[1]);
                    break;
                case "supply":
                default:
                    supply += Integer.parseInt(splittedCurrentLine[1]);
            }
        }
        report.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);
        return report.toString();
    }
}
