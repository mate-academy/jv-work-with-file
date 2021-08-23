package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int BUY_RESULT_INDEX = 1;
    private static final int SUPPLY_RESULT_INDEX = 0;
    private final int[] supplyAndBuyResults = new int[2];

    public void getStatistic(String fromFileName, String toFileName) {
        readSourceFile(fromFileName);
        createReportFile(toFileName);
        String report = buildReport(supplyAndBuyResults);
        writeReportToFile(report, toFileName);
    }

    private void readSourceFile(String fromFileName) {
        try (BufferedReader readerOfSourceFile = new BufferedReader(new FileReader(fromFileName))) {
            String line = readerOfSourceFile.readLine();
            while (line != null) {
                calculateSupplyAndBuyResults(line);
                line = readerOfSourceFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("FileNotFoundException: Be sure your source file is exist",
                    e);
        }
    }

    private void createReportFile(String toFileName) {
        new File(toFileName);
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writerToReportFile = new BufferedWriter(new FileWriter(toFileName))) {
            writerToReportFile.write(report);
        } catch (IOException e) {
            throw new RuntimeException("FileNotFoundException: Be sure your report file is exist",
                    e);
        }
    }

    private String buildReport(int[] supplyAndBuyResults) {
        StringBuilder reportBuilder = new StringBuilder();
        int reportResultValue = supplyAndBuyResults[SUPPLY_RESULT_INDEX]
                - supplyAndBuyResults[BUY_RESULT_INDEX];
        return reportBuilder.append("supply,")
                .append(supplyAndBuyResults[SUPPLY_RESULT_INDEX])
                .append(System.lineSeparator())
                .append("buy,")
                .append(supplyAndBuyResults[BUY_RESULT_INDEX])
                .append(System.lineSeparator())
                .append("result,")
                .append(reportResultValue).toString();
    }

    private void calculateSupplyAndBuyResults(String line) {
        String[] lineData = line.split(",");
        int lineIntegerValue = Integer.parseInt(lineData[1]);
        if (lineData[0].equals("supply")) {
            supplyAndBuyResults[SUPPLY_RESULT_INDEX] += lineIntegerValue;
        } else {
            supplyAndBuyResults[BUY_RESULT_INDEX] += lineIntegerValue;
        }
    }
}
