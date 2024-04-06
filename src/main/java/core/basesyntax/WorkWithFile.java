package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_POSITION = 0;
    private static final int NUMBER_POSITION = 1;

    private static final int STARTING_NUMBER = 0;
    private int suplyTotal = 0;
    private int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        suplyTotal = STARTING_NUMBER;
        buyTotal = STARTING_NUMBER;

        readFile(fromFileName);
        createReport(toFileName);
    }

    private void readFile(String fileName) {
        File readingFile = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(readingFile))) {
            String value = reader.readLine();
            while (value != null) {
                String[] divider = value.split(",");
                if (divider[NAME_POSITION].equals("supply")) {
                    suplyTotal += Integer.parseInt(divider[NUMBER_POSITION]);
                } else if (divider[NAME_POSITION].equals("buy")) {
                    buyTotal += Integer.parseInt(divider[NUMBER_POSITION]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read the file", e);
        }
    }

    private void createReport(String fileName) {
        StringBuilder report = new StringBuilder();
        File reportFile = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            report.append("supply,").append(suplyTotal).append(System.lineSeparator());
            report.append("buy,").append(buyTotal).append(System.lineSeparator());
            report.append("result,").append(suplyTotal - buyTotal);
            writer.write(report.toString());

        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to the file", e);
        }

    }
}
