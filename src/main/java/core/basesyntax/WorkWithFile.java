package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = createFile(fromFileName);
        File toFile = createFile(toFileName);
        String[] strings = getReadLines(fromFile);
        int supplyAmount = accountAmount(strings, SUPPLY);
        int buyAmount = accountAmount(strings, BUY);
        int resultAmount = supplyAmount - buyAmount;
        String report = createReport(supplyAmount, buyAmount, resultAmount);
        writeToFile(toFile, report);
    }

    private File createFile(String fileName) {
        return new File(fileName);
    }

    private int accountAmount(String[] strings, String typeOfAmount) {
        int amount = 0;
        for (String line : strings) {
            String[] typeAndAmount = line.split(COMMA);
            if (typeAndAmount.length == 2 && typeOfAmount.equals(SUPPLY)
                    && typeAndAmount[0].equals("supply")) {
                amount += Integer.parseInt(typeAndAmount[1]);
            }
            if (typeAndAmount.length == 2 && typeOfAmount.equals(BUY)
                    && typeAndAmount[0].equals("buy")) {
                amount += Integer.parseInt(typeAndAmount[1]);
            }
        }
        return amount;
    }

    private String[] getReadLines(File fromFile) {
        StringBuilder textFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line = reader.readLine();
            while (line != null) {
                textFromFile.append(NEW_LINE).append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from the file " + fromFile, e);
        }
        return textFromFile.toString().split(NEW_LINE);
    }

    private String createReport(int supplyAmount, int buyAmount, int resultAmount) {
        return SUPPLY + COMMA + supplyAmount + NEW_LINE
                + BUY + COMMA + buyAmount + NEW_LINE
                + RESULT + COMMA + resultAmount;
    }

    private void writeToFile(File toFile, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to the file " + toFile, e);
        }
    }
}
