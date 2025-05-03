package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] split = readFile(fromFileName).split("\\W+");
        String[][] statistic = statisticForReport(split);
        String[][] calculateStatisticForReport
                = calculateStatisticForReport(statistic);
        String report = createReport(calculateStatisticForReport);
        writeReportToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader
                     = new BufferedReader(new FileReader(fileName));) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return stringBuilder.toString();
    }

    private String[][] statisticForReport(String[] array) {
        String[][] statisticForReport = new String[array.length / 2][2];
        int index = 0;
        for (int i = 0; i < array.length; i = i + 2) {
            statisticForReport[index][0] = array[i];
            statisticForReport[index][1] = array[i + 1];
            index++;
        }
        return statisticForReport;
    }

    private String[][] calculateStatisticForReport(String[][] statisticForReport) {
        String[] statisticsFields = new String[]{"supply", "buy", "result"};
        String[][] resultOfMiscalculations = new String[3][2];
        for (int i = 0; i < resultOfMiscalculations.length; i++) {
            resultOfMiscalculations[i][0] = statisticsFields[i];
            resultOfMiscalculations[i][1] = "0";
        }
        for (String[] strings : statisticForReport) {
            for (int j = 0; j < resultOfMiscalculations.length; j++) {
                if (strings[0].equals(resultOfMiscalculations[j][0])) {
                    int tempSum = Integer.parseInt(strings[1])
                            + Integer.parseInt(resultOfMiscalculations[j][1]);
                    resultOfMiscalculations[j][1] = Integer.toString(tempSum);
                }
            }
        }
        int result = Integer.parseInt(resultOfMiscalculations[0][1])
                - Integer.parseInt(resultOfMiscalculations[1][1]);
        resultOfMiscalculations[2][1] = Integer.toString(result);
        return resultOfMiscalculations;
    }

    private String createReport(String[][] calculateStatisticForReport) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < calculateStatisticForReport.length; i++) {
            stringBuilder.append(calculateStatisticForReport[i][0])
                    .append(",")
                    .append(calculateStatisticForReport[i][1])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private void writeReportToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedReader
                     = new BufferedWriter(new FileWriter(file, true));) {
            bufferedReader.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write date to file", e);
        }
    }
}
