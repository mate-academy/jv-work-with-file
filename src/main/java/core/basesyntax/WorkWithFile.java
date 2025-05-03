package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String FILE_SEPARATOR = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int OPERATION_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeReportToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();
        String fromFileLine;
        try (BufferedReader buffer = new BufferedReader(new FileReader(fromFileName))) {
            fromFileLine = buffer.readLine();
            while (fromFileLine != null) {
                fileContent.append(System.lineSeparator()).append(fromFileLine);
                fromFileLine = buffer.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
        return fileContent.toString().split(System.lineSeparator());
    }

    private Report createReport(String[] fileContent) {
        String[] lineArray;
        Report report = new Report();
        for (String line : fileContent) {
            lineArray = line.split(FILE_SEPARATOR);
            if (lineArray[OPERATION_INDEX].equals(SUPPLY)) {
                report.calculateSupplySum(Integer.parseInt(lineArray[VALUE_INDEX]));
            }
            if (lineArray[OPERATION_INDEX].equals(BUY)) {
                report.calculateBuySum(Integer.parseInt(lineArray[VALUE_INDEX]));
            }
        }
        report.calculateResult();
        return report;
    }

    private void writeReportToFile(Report report, String toFileName) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(System.lineSeparator()).append(SUPPLY).append(FILE_SEPARATOR)
                .append(report.getSupplySum()).append(System.lineSeparator()).append(BUY)
                .append(FILE_SEPARATOR).append(report.getBuySum()).append(System.lineSeparator())
                .append(RESULT).append(FILE_SEPARATOR).append(report.getResult());
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferWriter.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }

    private static class Report {
        private int supplySum = 0;
        private int buySum = 0;
        private int result = 0;

        public void calculateSupplySum(int supplyAdd) {
            supplySum += supplyAdd;
        }

        public void calculateBuySum(int buyAdd) {
            buySum += buyAdd;
        }

        public void calculateResult() {
            result = supplySum - buySum;
        }

        public int getSupplySum() {
            return supplySum;
        }

        public int getBuySum() {
            return buySum;
        }

        public int getResult() {
            return result;
        }
    }
}
