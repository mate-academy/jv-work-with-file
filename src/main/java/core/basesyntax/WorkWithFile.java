package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        createReportFile(toFileName);
        String readerResult = readFile(fromFileName);
        int supplyResult = calculateSupplyResult(readerResult);
        int buyResult = calculateBuyResult(readerResult);
        int resultValue = calculateResultValue(supplyResult, buyResult);
        String report = buildReport(supplyResult, buyResult, resultValue);
        writeReportToFile(report, toFileName);
    }

    private void createReportFile(String toFileName) {
        new File(toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder readerResultBuilder = new StringBuilder();
        try (BufferedReader readerOfSourceFile = new BufferedReader(new FileReader(fileName))) {
            String line = readerOfSourceFile.readLine();
            while (line != null) {
                readerResultBuilder.append(line).append(System.lineSeparator());
                line = readerOfSourceFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("FileNotFoundException: Be sure your source file is exist",
                    e);
        }
        return readerResultBuilder.toString().trim();
    }

    private int calculateSupplyResult(String readerResult) {
        int supplyResult = 0;
        for (String line : readerResult.split(System.lineSeparator())) {
            String[] lineData = line.split(",");
            int lineIntegerValue = Integer.parseInt(lineData[1]);
            if (lineData[0].equals("supply")) {
                supplyResult += lineIntegerValue;
            }
        }
        return supplyResult;
    }

    private int calculateBuyResult(String readerResult) {
        int buyResult = 0;
        for (String line : readerResult.split(System.lineSeparator())) {
            String[] lineData = line.split(",");
            int lineIntegerValue = Integer.parseInt(lineData[1]);
            if (lineData[0].equals("buy")) {
                buyResult += lineIntegerValue;
            }
        }
        return buyResult;
    }

    private int calculateResultValue(int supplyResult, int buyResult) {
        int resultValue = supplyResult - buyResult;
        return resultValue;
    }

    private String buildReport(int supplyResult, int buyResult, int resultValue) {
        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder.append("supply,")
                .append(supplyResult)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyResult)
                .append(System.lineSeparator())
                .append("result,")
                .append(resultValue).toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writerToReportFile = new BufferedWriter(new FileWriter(toFileName))) {
            writerToReportFile.write(report);
        } catch (IOException e) {
            throw new RuntimeException("FileNotFoundException: Be sure your report file is exist",
                    e);
        }
    }

}
