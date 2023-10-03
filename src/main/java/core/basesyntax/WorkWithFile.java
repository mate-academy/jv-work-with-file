package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DEFAULT_OPERATIONS_NUMBER = 2;
    private static final String FIRST_OPERATION_NAME = "supply";
    private static final String SECOND_OPERATION_NAME = "buy";
    private static final String RESULT_OPERATION_NAME = "result";
    private static final String SYMBOL_COMMA = ",";
    private static final String SYMBOL_LINE_SEPARATOR = "\n";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculateReport(readFile(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to the file " + toFileName, e);
        }
    }

    private String readFile(String fromFileName) {
        StringBuilder readFileBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int charTextValue = bufferedReader.read();
            while (charTextValue != -1) {
                readFileBuilder.append((char) charTextValue);
                charTextValue = bufferedReader.read();
            }
            return readFileBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read data from the file " + fromFileName, e);
        }
    }

    private String calculateReport(String readFile) {
        int supply = 0;
        int buy = 0;
        int parsedValue;
        String[] separatedText = readFile.split(SYMBOL_LINE_SEPARATOR);
        String[][] statisticTable = new String[separatedText.length][DEFAULT_OPERATIONS_NUMBER];
        for (int i = 0; i < separatedText.length; i++) {
            for (int j = 0; j < DEFAULT_OPERATIONS_NUMBER; j++) {
                statisticTable[i][j] = separatedText[i].split(SYMBOL_COMMA)[j];
            }
        }
        for (int i = 0; i < statisticTable.length; i++) {
            parsedValue = Integer.parseInt(statisticTable[i][1]);
            supply += statisticTable[i][0].equals(FIRST_OPERATION_NAME) ? parsedValue : 0;
            buy += statisticTable[i][0].equals(SECOND_OPERATION_NAME) ? parsedValue : 0;
        }
        StringBuilder statisticResultBuilder = new StringBuilder();
        statisticResultBuilder.append(FIRST_OPERATION_NAME).append(SYMBOL_COMMA)
                .append(supply).append(System.lineSeparator())
                .append(SECOND_OPERATION_NAME).append(SYMBOL_COMMA)
                .append(buy).append(System.lineSeparator())
                .append(RESULT_OPERATION_NAME).append(SYMBOL_COMMA).append(supply - buy);
        return statisticResultBuilder.toString();
    }
}
