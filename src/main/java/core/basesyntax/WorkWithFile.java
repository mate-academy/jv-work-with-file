package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeReportToFile(getReport(readInformationFromFile(fromFileName)), toFileName);
    }

    private void writeReportToFile(String[] reportMessage, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter reportWriter = new BufferedWriter(new FileWriter(file))) {
            for (String line : reportMessage) {
                reportWriter.write(line);
                reportWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into the file.: " + file.getName() + e);
        }
    }

    private String[] getReport(String[] information) {
        StringBuilder reportBuilder = new StringBuilder();
        int buyAmount = 0;
        int supplyAmount = 0;
        for (String line : information) {
            String[] values = line.split(",");
            switch (values[OPERATION]) {
                case "buy" -> buyAmount += Integer.parseInt(values[AMOUNT]);
                case "supply" -> supplyAmount += Integer.parseInt(values[AMOUNT]);
                default -> System.out.println("Wrong format.");
            }
        }
        reportBuilder.append("supply,").append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy,").append(buyAmount)
                .append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount);
        return reportBuilder.toString().split(System.lineSeparator());
    }

    private String[] readInformationFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder informationBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                informationBuilder.append(line).append(System.lineSeparator());
            }
            return informationBuilder.toString().split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found.: " + file.getName() + e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file.: " + file.getName() + e);
        }
    }
}

