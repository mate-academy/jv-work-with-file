package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String SUPPLY_LINE_NAME = "supply";
    private static final String BUY_LINE_NAME = "buy";
    private static final String RESULT_LINE_NAME = "result";
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(report, toFileName);
    }

    private ArrayList<String> readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            ArrayList<String> separatedLines = new ArrayList<>();
            while (line != null) {
                separatedLines.add(line);
                line = reader.readLine();
            }
            return separatedLines;
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
    }

    private String generateReport(ArrayList<String> data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        int amount;
        String operation;
        String[] separatedLine;
        StringBuilder report = new StringBuilder();
        for (String line : data) {
            separatedLine = line.split(DELIMITER);
            operation = separatedLine[OPERATION_INDEX];
            amount = Integer.parseInt(separatedLine[AMOUNT_INDEX]);
            if (SUPPLY_LINE_NAME.equals(operation)) {
                supplyTotal += amount;
            } else {
                buyTotal += amount;
            }
        }
        return report.append(SUPPLY_LINE_NAME).append(DELIMITER)
                .append(supplyTotal).append(System.lineSeparator())
                .append(BUY_LINE_NAME).append(DELIMITER)
                .append(buyTotal).append(System.lineSeparator())
                .append(RESULT_LINE_NAME).append(DELIMITER)
                .append(supplyTotal - buyTotal).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file" + toFileName, e);
        }
    }
}
