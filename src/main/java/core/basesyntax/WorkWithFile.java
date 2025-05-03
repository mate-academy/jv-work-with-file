package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFileBuilder = readDataFromFile(fromFileName);
        String finalData = createReport(textFromFileBuilder);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(finalData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file.", e);
        }
    }

    private String readDataFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder fileStrings = new StringBuilder();
            String fromFile = reader.readLine();
            while (fromFile != null) {
                fileStrings.append(fromFile)
                        .append(System.lineSeparator());
                fromFile = reader.readLine();
            }
            return fileStrings.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file.", e);
        }
    }

    private String createReport(String fileStrings) {
        StringBuilder report = new StringBuilder();
        String[] splittedLines = fileStrings.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String currentLine : splittedLines) {
            String[] splittedCurrentLine = currentLine.split(",");
            switch (splittedCurrentLine[0]) {
                case "buy":
                    buy += Integer.parseInt(splittedCurrentLine[1]);
                    break;
                case "supply":
                    supply += Integer.parseInt(splittedCurrentLine[1]);
                    break;
                default:
                    throw new RuntimeException("Unexpected word in file.");
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
