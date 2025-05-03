package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String CSV_OPERATION_TYPE = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName).split(System.lineSeparator());
        String reportFromInputFile = createReport(dataFromFile);
        writeToFile(reportFromInputFile, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder strBuilder = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(fromFileName))) {
            String fileLine = "";
            while ((fileLine = bf.readLine()) != null) {
                strBuilder.append(fileLine).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return strBuilder.toString();
    }

    private String createReport(String[] report) {
        int countBuy = 0;
        int countSupply = 0;
        StringBuilder reportInfo = new StringBuilder();
        for (String reportElement : report) {
            String[] reportData = reportElement.split(CSV_SEPARATOR);
            if (reportData[OPERATION_INDEX].equals(CSV_OPERATION_TYPE)) {
                countBuy += Integer.parseInt(reportData[AMOUNT_INDEX]);
            } else {
                countSupply += Integer.parseInt(reportData[AMOUNT_INDEX]);
            }
        }
        reportInfo.append("supply,").append(countSupply).append(System.lineSeparator());
        reportInfo.append("buy,").append(countBuy).append(System.lineSeparator());
        reportInfo.append("result,").append(countSupply - countBuy);
        return reportInfo.toString();
    }
}
