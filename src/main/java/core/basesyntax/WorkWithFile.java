package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATOR_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readFromFle(fromFileName));
        writeToFile(toFileName, report);
    }

    private String[] readFromFle(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder allLines = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                allLines.append(line).append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }

        return allLines.toString().split(" ");
    }

    private String createReport(String[] data) {
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0; i < data.length; i++) {
            String[] operatorAndAmount = data[i].split(",");
            if (operatorAndAmount[OPERATOR_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(operatorAndAmount[AMOUNT_INDEX]);
            } else {
                buySum += Integer.parseInt(operatorAndAmount[AMOUNT_INDEX]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}
