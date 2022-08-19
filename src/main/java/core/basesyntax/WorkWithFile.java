package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int REPORT_LENGTH = 3;
    private static final int RECORD_DETAIL_TYPE_INDEX = 0;
    private static final int RECORD_DETAIL_AMOUNT_INDEX = 1;
    private static final int SUPPLY_ROW_INDEX = 0;
    private static final int BUY_ROW_INDEX = 1;
    private static final int RESULT_ROW_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] records = readFromFile(fromFileName);
        String report = getReport(records);
        writeReportToFile(toFileName, report.split(System.lineSeparator()));
    }

    private String[] readFromFile(String fromFileName) {
        String[] records;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder recordsBuilder = new StringBuilder();
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                recordsBuilder.append(currentLine).append(System.lineSeparator());
                currentLine = bufferedReader.readLine();
            }
            records = recordsBuilder.toString().split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return records;
    }

    private void writeReportToFile(String targetFileName, String[] report) {
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
            throw new RuntimeException("Can't write data to file: " + targetFileName, e);
        }
    }

    private String getReport(String[] records) {
        int[] totals = calculateTotals(records);
        StringBuilder reportBuilder = new StringBuilder();
        for (int i = 0; i < totals.length; i++) {
            reportBuilder.append(getOperationType(i)).append(totals[i])
                    .append(System.lineSeparator());
        }
        return reportBuilder.toString();
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
        totals[RESULT_ROW_INDEX] = totals[SUPPLY_ROW_INDEX] - totals[BUY_ROW_INDEX];
        return totals;
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
