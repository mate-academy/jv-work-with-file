package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SPLIT_REGEX = "[,\\n]";
    private static final String COMMA_CONSTANT = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SUPPLY_CONSTANT = "supply";
    private static final String BUY_CONSTANT = "buy";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        int[] spreadsheetData = calculateSpreadsheetData(data);
        String resultData = prepareResultString(spreadsheetData[SUPPLY_INDEX],
                spreadsheetData[BUY_INDEX], spreadsheetData[RESULT_INDEX]);
        writeToFile(toFileName, resultData);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file from the file " + fromFileName, e);
        }
    }

    private int[] calculateSpreadsheetData(List<String> data) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String line : data) {
            String[] splitLine = line.split(SPLIT_REGEX);
            if (splitLine.length == 2) {
                String operationType = splitLine[OPERATION_TYPE_INDEX].trim();
                int amount = Integer.parseInt(splitLine[AMOUNT_INDEX].trim());
                if (operationType.equals(SUPPLY_CONSTANT)) {
                    supply += amount;
                } else if (operationType.equals(BUY_CONSTANT)) {
                    buy += amount;
                }
            }
            result = supply - buy;
        }
        return new int[]{supply, buy, result};
    }

    private String prepareResultString(int supply, int buy, int result) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY_CONSTANT)
                .append(COMMA_CONSTANT)
                .append(supply)
                .append(NEW_LINE);
        stringBuilder
                .append(BUY_CONSTANT)
                .append(COMMA_CONSTANT)
                .append(buy)
                .append(NEW_LINE);
        stringBuilder
                .append("result")
                .append(COMMA_CONSTANT)
                .append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
