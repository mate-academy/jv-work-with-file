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
    private static final int FIRST_ELEMENT_INDEX = 0;
    private static final int SECOND_ELEMENT_INDEX = 1;
    private static final String EMPTY_STRING = "";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readInputFile(fromFileName);
        String report = EMPTY_STRING;
        if (!data.isEmpty()) {
            report += calculateStatistic(data);
        }
        if (!report.isEmpty()) {
            writeReportToFile(report, toFileName);
        }
    }

    private String readInputFile(String pathToFile) {
        File file = new File(pathToFile);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            if (line == null) {
                return EMPTY_STRING;
            }
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
        String[] arrayData = inputString.split(System.lineSeparator());
        int suppliesCount = 0;
        int buyCount = 0;
        for (String simpleLine : arrayData) {
            String[] lineToArray = simpleLine.split(WORDS_DIVIDER);
            if (lineToArray[FIRST_ELEMENT_INDEX].equals(SUPPLY_OPERATION)) {
                suppliesCount += Integer.parseInt(lineToArray[SECOND_ELEMENT_INDEX]);
            } else {
                buyCount += Integer.parseInt(lineToArray[SECOND_ELEMENT_INDEX]);
            }
        }
        return SUPPLY_OPERATION + WORDS_DIVIDER + suppliesCount + System.lineSeparator()
                + BUY_OPERATION + WORDS_DIVIDER + buyCount + System.lineSeparator()
                + RESULT_OPERATION + WORDS_DIVIDER + (suppliesCount - buyCount);
    }

    private void writeReportToFile(String reportToWrite, String pathToFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToFile))) {
            bufferedWriter.write(reportToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + e);
        }
    }
}
