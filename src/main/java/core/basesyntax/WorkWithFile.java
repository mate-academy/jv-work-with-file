package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WHITESPACE_REGEX = "\\s";
    private static final int REPORT_LENGTH = 3;
    private static final int RECORD_DETAIL_TYPE_INDEX = 0;
    private static final int RECORD_DETAIL_AMOUNT_INDEX = 1;
    private static final int SUPPLY_ROW_INDEX = 0;
    private static final int BUY_ROW_INDEX = 1;
    private static final int RESULT_ROW_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] records = readFromFile(fromFileName);
        String[] report = getReport(records);
        writeReportToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        String[] records;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder recordsBuilder = new StringBuilder();
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                recordsBuilder.append(currentLine).append(" ");
                currentLine = bufferedReader.readLine();
            }
            records = recordsBuilder.toString().split(WHITESPACE_REGEX);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return records;
    }

    public void writeReportToFile(String targetFileName, String[] report) {
        try {
            File reportFile = new File(targetFileName);
            reportFile.createNewFile();
            BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFile, true));
            for (String reportLine : report) {
                reportWriter.write(reportLine + System.lineSeparator());
                reportWriter.flush();
            }
            reportWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String[] getReport(String[] records) {
        String[] report = new String[REPORT_LENGTH];
        fillReport(report, records);
        return report;
    }

    private void fillReport(String[] report, String[] records) {
        int[] totals = calculateTotals(records);
        calculateResult(totals);
        StringBuilder reportBuilder = new StringBuilder();
        for (int i = 0; i < report.length; i++) {
            report[i] = reportBuilder.append(getOperationType(i)).append(totals[i]).toString();
            reportBuilder.setLength(0);
        }
    }

    private int[] calculateTotals(String[] records) {
        int[] totals = new int[REPORT_LENGTH];
        for (String record : records) {
            String[] recordDetails = record.split(",");
            switch (recordDetails[RECORD_DETAIL_TYPE_INDEX]) {
                case "supply":
                    totals[SUPPLY_ROW_INDEX] +=
                            Integer.parseInt(recordDetails[RECORD_DETAIL_AMOUNT_INDEX]);
                    break;
                case "buy":
                default:
                    totals[BUY_ROW_INDEX] +=
                            Integer.parseInt(recordDetails[RECORD_DETAIL_AMOUNT_INDEX]);
                    break;
            }
        }
        return totals;
    }

    private void calculateResult(int[] totals) {
        totals[RESULT_ROW_INDEX] = totals[SUPPLY_ROW_INDEX] - totals[BUY_ROW_INDEX];
    }

    private String getOperationType(int reportRowIndex) {
        switch (reportRowIndex) {
            case SUPPLY_ROW_INDEX:
                return "supply,";
            case BUY_ROW_INDEX:
                return "buy,";
            case RESULT_ROW_INDEX:
            default:
                return "result,";
        }
    }
}
