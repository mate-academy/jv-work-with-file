package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATOR = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readFromFle(fromFileName));
        writeToFile(toFileName, report);
    }

    public static String[] readFromFle(String fromFileName) {
        if (fromFileName == null) {
            return new String[0];
        }
        File file = new File(fromFileName);
        if (file.length() == 0) {
            return new String[0];
        }
        StringBuilder allLines = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int character = reader.read();
            while (character != -1) {
                allLines.append((char)character);
                character = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }

        return allLines.toString().split("\\s+");
    }

    public static String createReport(String[] data) {
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0; i < data.length; i++) {
            String[] operatorAndAmount = data[i].split(",");
            if (operatorAndAmount[OPERATOR].equals("supply")) {
                supplySum += Integer.parseInt(operatorAndAmount[AMOUNT]);
            } else {
                buySum += Integer.parseInt(operatorAndAmount[AMOUNT]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum);
        return report.toString();
    }

    public static void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}
