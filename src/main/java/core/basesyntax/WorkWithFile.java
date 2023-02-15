package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileText = readFromFile(fromFileName);
        String report = createReport(fileText);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder fileText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                fileText.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read from this file: " + fileName, e);
        }
        return fileText.toString();
    }

    private String createReport(String fileText) {
        String[] fileTextArray = fileText.split(System.lineSeparator());
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : fileTextArray) {
            String[] lineParts = line.split(DELIMITER);
            if (lineParts[OPERATION_INDEX].equals("supply")) {
                totalSupply += Integer.valueOf(lineParts[AMOUNT_INDEX]);
            } else {
                totalBuy += Integer.valueOf(lineParts[AMOUNT_INDEX]);
            }
        }
        int totalResult = totalSupply - totalBuy;
        StringBuilder report = new StringBuilder();
        report.append("supply,")
                .append(totalSupply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(totalBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(totalResult);
        return report.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("can't write to this file " + fileName, e);
        }
    }
}
