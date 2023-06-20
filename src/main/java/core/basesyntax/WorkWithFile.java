package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] incomeData = getIncomeData(fromFileName);
        String reportData = getReportData(incomeData);
        writeReportToFile(toFileName, reportData);
    }

    private String[] getIncomeData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File incomeData = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(incomeData))) {
            String lineValue = reader.readLine();
            while (lineValue != null) {
                stringBuilder.append(lineValue).append(COMMA);
                lineValue = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file " + fromFileName, e);
        }
        return stringBuilder.toString().split(COMMA);
    }

    private String getReportData(String[] incomeData) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < incomeData.length; i = i + 2) {
            if (incomeData[i].equals(SUPPLY_OPERATION)) {
                supply = supply + Integer.parseInt(incomeData [i + 1]);
                continue;
            }
            if (incomeData[i].equals(BUY_OPERATION)) {
                buy = buy + Integer.parseInt(incomeData[i + 1]);
            }
        }
        return SUPPLY_OPERATION + COMMA + supply + System.lineSeparator()
                + BUY_OPERATION + COMMA + buy + System.lineSeparator()
                + RESULT + COMMA + (supply - buy);
    }

    private void writeReportToFile(String toFileName, String reportData) {
        File resultData = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultData))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
