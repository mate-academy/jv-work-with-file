package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_SYMBOL = " ";
    private static final String SPLIT_COMMA = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readTheFile(fromFileName);
        String textReport = prepareReport(linesFromFile);
        writeIntoFile(toFileName, textReport);
    }

    private String prepareReport(String[] linesFromFile) {
        StringBuilder report = new StringBuilder();
        int supply = 0;
        int buy = 0;

        for (String line : linesFromFile) {
            String[] lineElements = line.split(SPLIT_COMMA);
            if (OPERATION_SUPPLY.equals(lineElements[OPERATION_INDEX])) {
                supply += Integer.parseInt(lineElements[AMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(lineElements[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        report.append(OPERATION_SUPPLY).append(SPLIT_COMMA).append(supply)
                .append(System.lineSeparator())
                .append(OPERATION_BUY).append(SPLIT_COMMA).append(buy)
                .append(System.lineSeparator())
                .append(OPERATION_RESULT).append(SPLIT_COMMA).append(result);
        return report.toString();
    }

    public String[] readTheFile(String file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder lineCollection = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineCollection.append(line).append(SPLIT_SYMBOL);
            }
            String finalText = lineCollection.toString();
            return finalText.length() == 0 ? new String[0] : finalText.split(SPLIT_SYMBOL);
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file: " + file, e);
        }
    }

    public void writeIntoFile(String file, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file, e);
        }
    }
}
