package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String PARAMETER_0_SUPPLY = "supply";
    private static final String PARAMETER_1_BUY = "buy";
    private static final String PARAMETER_2_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] incomeData = getIncomeData(fromFileName);
        int[] reportNumbers = getReportNumbers(incomeData);
        writeReportToFile(toFileName, reportNumbers);
    }

    private String[] getIncomeData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] resultData;
        File incomeData = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(incomeData))) {
            String lineValue = reader.readLine();
            while (lineValue != null) {
                stringBuilder.append(lineValue).append(",");
                System.out.println(lineValue);
                lineValue = reader.readLine();
            }
            resultData = stringBuilder.toString().split(",");
        } catch (IOException e) {
            throw new RuntimeException("Can't open file", e);
        }
        return resultData;
    }

    private int[] getReportNumbers(String[] incomeData) {
        int supply = 0;
        int buy = 0;
        int result;
        for (int i = 0; i < incomeData.length; i = i + 2) {
            if (incomeData[i].equals(PARAMETER_0_SUPPLY)) {
                supply = supply + Integer.parseInt(incomeData [i + 1]);
                continue;
            }
            if (incomeData[i].equals(PARAMETER_1_BUY)) {
                buy = buy + Integer.parseInt(incomeData[i + 1]);
            }
        }
        result = supply - buy;
        return new int[] {supply, buy, result};
    }

    private void writeReportToFile(String toFileName, int[] reportNumbers) {
        String[] parameters = new String[] {PARAMETER_0_SUPPLY, PARAMETER_1_BUY,
                PARAMETER_2_RESULT};
        File resultData = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultData))) {
            for (int i = 0; i < parameters.length; i++) {
                bufferedWriter.write(parameters[i] + ","
                        + reportNumbers[i] + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
