package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static int SUPPLY_INDEX = 0;
    private static int AMOUNT_INDEX = 1;
    private static String SUPPLY_NAME = "supply";
    private static String BUY_NAME = "buy";
    private static String RESULT_NAME = "result";
    private static String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        for (String string : readFromFile(fromFileName).split(System.lineSeparator())) {
            String[] stringInfo = string.split(REGEX);
            if (stringInfo[SUPPLY_INDEX].equals(SUPPLY_NAME)) {
                supplySum += Integer.parseInt(stringInfo[AMOUNT_INDEX]);
            } else {
                buySum += Integer.parseInt(stringInfo[AMOUNT_INDEX]);
            }
        }
        String report = getReport(supplySum, buySum);
        writeInfoInFile(report, toFileName);
    }

    private String getReport(int supplySum, int buySum) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_NAME).append(REGEX)
                .append(supplySum).append(System.lineSeparator())
                .append(BUY_NAME).append(REGEX)
                .append(buySum).append(System.lineSeparator())
                .append(RESULT_NAME).append(REGEX)
                .append(supplySum - buySum).append(System.lineSeparator());
        return report.toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String oneLine = reader.readLine();
            while (oneLine != null) {
                stringBuilder.append(oneLine).append(System.lineSeparator());
                oneLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return stringBuilder.toString();
    }

    private void writeInfoInFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write report into file", e);
        }
    }
}
