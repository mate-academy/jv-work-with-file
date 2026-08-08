package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String CSV_SEPARATOR = ",";
    public static final String WORD_SUPPLY = "supply";
    public static final String WORD_BUY = "buy";
    public static final String WORD_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String statistics = readFile(fromFileName);
        String report = statisticCalculation(statistics);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String oneLine = bufferedReader.readLine();
            while (oneLine != null) {
                stringBuilder.append(oneLine).append(System.lineSeparator());
                oneLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        return stringBuilder.toString();
    }

    private String statisticCalculation(String statistics) {
        String[] separateStatisticsLines = statistics.split(System.lineSeparator());

        int supplySum = 0;
        int buySum = 0;
        for (String separateStatisticsLine : separateStatisticsLines) {
            String[] categoryAndValue = separateStatisticsLine.split(CSV_SEPARATOR);
            if (categoryAndValue[0].equals("supply")) {
                supplySum += Integer.parseInt(categoryAndValue[1]);
            } else {
                buySum += Integer.parseInt(categoryAndValue[1]);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(WORD_SUPPLY).append(CSV_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(WORD_BUY).append(CSV_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(WORD_RESULT).append(CSV_SEPARATOR).append(supplySum - buySum);

        return stringBuilder.toString();
    }

    private void writeToFile(String statistics, String toFileName) {
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(statistics);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
