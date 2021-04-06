package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WORDS_DIVIDER = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int AMOUNT_SUPPLY_OPERATION_INDEX = 0;
    private static final int AMOUNT_BUY_OPERATION_INDEX = 1;
    private static final String EMPTY_STRING = "";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readInputFile(fromFileName);
        String report = calculateStatistic(data);
        writeReportToFile(report, toFileName);
    }

    private String readInputFile(String pathToFile) {
        File file = new File(pathToFile);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file, e);
        }
    }

    private String calculateStatistic(String inputString) {
        String[] statistic = inputString.split(System.lineSeparator());
        int suppliesCount = 0;
        int buyCount = 0;
        for (String simpleLine : statistic) {
            String[] lineToArray = simpleLine.split(WORDS_DIVIDER);
            if (lineToArray[OPERATION_TYPE_INDEX].equals(SUPPLY_OPERATION)) {
                suppliesCount += Integer.parseInt(lineToArray[AMOUNT_INDEX]);
            } else {
                buyCount += Integer.parseInt(lineToArray[AMOUNT_INDEX]);
            }
        }
        return SUPPLY_OPERATION + WORDS_DIVIDER + suppliesCount + System.lineSeparator()
                + BUY_OPERATION + WORDS_DIVIDER + buyCount + System.lineSeparator()
                + RESULT_OPERATION + WORDS_DIVIDER + (suppliesCount - buyCount);
    }

    private String createReport(String statisticResult) {
        String[] statisticArray = statisticResult.split(EMPTY_STRING);
        return SUPPLY_OPERATION + WORDS_DIVIDER + statisticArray[AMOUNT_SUPPLY_OPERATION_INDEX]
                + System.lineSeparator()
                + BUY_OPERATION + WORDS_DIVIDER + statisticArray[AMOUNT_BUY_OPERATION_INDEX]
                + System.lineSeparator()
                + RESULT_OPERATION + WORDS_DIVIDER
                + (Integer.parseInt(statisticArray[AMOUNT_SUPPLY_OPERATION_INDEX])
                - Integer.parseInt(statisticArray[AMOUNT_BUY_OPERATION_INDEX]));
    }

    private void writeReportToFile(String reportToWrite, String pathToFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToFile))) {
            bufferedWriter.write(reportToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + e);
        }
    }
}
