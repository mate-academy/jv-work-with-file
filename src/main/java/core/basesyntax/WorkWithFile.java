package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public static final String SUPPLY_STR = "supply";
    public static final String BUY_STR = "buy";
    public static final String RESULT_STR = "result";
    public static final String SEPARATOR = ",";
    public static final int STRING_VALUE_INDEX = 0;
    public static final int NUMBER_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputData = readStringsFromFile(fromFileName);
        String report = createReport(inputData);
        writeToFile(toFileName, report);
    }

    private String[] readStringsFromFile(String filePath) {
        try {
            return Files.readAllLines(new File(filePath).toPath())
                    .toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + filePath, e);
        }
    }

    private void writeToFile(String filePath, String data) {
        try {
            Files.write(new File(filePath).toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + filePath, e);
        }
    }

    private String createReport(String[] inputData) {
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String inputEntry : inputData) {
            String[] inputEntrySplited = inputEntry.split(SEPARATOR);
            if (inputEntrySplited[STRING_VALUE_INDEX].equals(SUPPLY_STR)) {
                supplyAmount += Integer.valueOf(inputEntrySplited[NUMBER_VALUE_INDEX]);
            } else {
                buyAmount += Integer.valueOf(inputEntrySplited[NUMBER_VALUE_INDEX]);
            }
        }
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY_STR).append(SEPARATOR).append(supplyAmount)
                .append(System.lineSeparator()).append(BUY_STR).append(SEPARATOR).append(buyAmount)
                .append(System.lineSeparator()).append(RESULT_STR).append(SEPARATOR)
                .append(supplyAmount - buyAmount).toString();
    }
}
