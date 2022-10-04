package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String EXCEPTION_TEXT = "Can't read file";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = ",";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String createReport(String supplyBuyResult) {
        String[] supplyBuy = supplyBuyResult.split(LINE_SEPARATOR);
        StringBuilder result = new StringBuilder();
        int supply = Integer.parseInt(supplyBuy[SUPPLY_INDEX]);
        int buy = Integer.parseInt(supplyBuy[BUY_INDEX]);
        result.append(SUPPLY).append(LINE_SEPARATOR).append(supply)
                .append(System.lineSeparator());
        result.append(BUY).append(LINE_SEPARATOR).append(buy)
                .append(System.lineSeparator());
        result.append(RESULT).append(LINE_SEPARATOR).append(supply - buy)
                .append(System.lineSeparator());
        return result.toString();
    }

    private void writeToFile(String result, String toFileName) {
        File outputFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_TEXT, e);
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder result = new StringBuilder();
        File inputFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                String[] wordsFromFileLine = fileLine.split(LINE_SEPARATOR);
                for (int i = 0; i < wordsFromFileLine.length; i++) {
                    if (wordsFromFileLine[i].equals(SUPPLY)) {
                        supply = supply + Integer.parseInt(wordsFromFileLine[i + 1]);
                    }
                    if (wordsFromFileLine[i].equals(BUY)) {
                        buy = buy + Integer.parseInt(wordsFromFileLine[i + 1]);
                    }
                }
                fileLine = bufferedReader.readLine();
            }
            result.append(supply).append(LINE_SEPARATOR).append(buy);
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_TEXT, e);
        }
        return result.toString();
    }
}
