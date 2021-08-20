package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION = 0;
    private static final int NUMBER = 1;
    private static final int SUPPLIED = 0;
    private static final int BOUGHT = 1;
    private static final int RESULT = 2;
    private static final int NUMBER_OF_ROWS = 3;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_STR = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeInFile(reportMaker(statisticCalculator(readFile(fromFileName))), toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                result.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!" + fromFileName, e);
        }
        return result.toString();
    }

    private int[] statisticCalculator(String textFromFile) {
        String[] rows = textFromFile.split("\\R");
        int[] reportData = new int[NUMBER_OF_ROWS];
        for (String row: rows) {
            String[] rowData = row.split(COMA);
            if (rowData[ACTION].equals(SUPPLY)) {
                reportData[SUPPLIED] += Integer.parseInt(rowData[NUMBER]);
            } else if (rowData[ACTION].equals(BUY)) {
                reportData[BOUGHT] += Integer.parseInt(rowData[NUMBER]);
            }
            reportData[RESULT] = reportData[SUPPLIED] - reportData[BOUGHT];
        }
        return reportData;
    }

    private String[] reportMaker(int[] reportData) {
        String[] output = new String[NUMBER_OF_ROWS];
        output[SUPPLIED] = SUPPLY + COMA + reportData[SUPPLIED];
        output[BOUGHT] = BUY + COMA + reportData[BOUGHT];
        output[RESULT] = RESULT_STR + COMA + reportData[RESULT];
        return output;
    }

    private void writeInFile(String[] content, String toFile) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(content[SUPPLIED]);
            bufferedWriter.newLine();
            bufferedWriter.write(content[BOUGHT]);
            bufferedWriter.newLine();
            bufferedWriter.write(content[RESULT]);
        } catch (IOException e) {
            throw new RuntimeException("Can't access file!" + toFile, e);
        }
    }

}
