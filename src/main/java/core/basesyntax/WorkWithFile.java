package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class WorkWithFile {
    private static final String SYMBOL_COMMA = ",";
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";
    private static final int FIELD_VALUES = 1;
    private static final int SUPPLY_OR_BUY = 0;
    private static final int SUPPLY_RESULT = 0;
    private static final int BUY_RESULT = 1;
    private static final int DIFFERENCE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        try {
            String[] dataFromFile = Files.newBufferedReader(Paths.get(fromFileName))
                    .lines()
                    .collect(Collectors.joining(" "))
                    .split(" ");
            return dataFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from this File", e);
        }
    }

    private int[] calculator(String[] dataFromFile) {
        String[] oneLine;
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String string : dataFromFile) {
            oneLine = string.split(SYMBOL_COMMA);
            Integer integer = Integer.parseInt(oneLine[FIELD_VALUES ]);
            if (oneLine[SUPPLY_OR_BUY].equals(SUPPLY_STRING)) {
                supply += integer;
            }
            if (oneLine[SUPPLY_OR_BUY].equals(BUY_STRING)) {
                buy += integer;
            }
            result = supply - buy;
        }
        return new int[] {supply, buy, result};
    }

    private String createReport(String[] dataFromFile) {
        int[] calculatedData = calculator(dataFromFile);
        return new StringBuilder().append(SUPPLY_STRING)
                .append(SYMBOL_COMMA)
                .append(calculatedData[SUPPLY_RESULT])
                .append(System.lineSeparator())
                .append(BUY_STRING)
                .append(SYMBOL_COMMA)
                .append(calculatedData[BUY_RESULT])
                .append(System.lineSeparator())
                .append(RESULT_STRING)
                .append(SYMBOL_COMMA)
                .append(calculatedData[DIFFERENCE]).toString();
    }

    private void writeToFile(String fileName, String report) {
        try {
            Files.write(Paths.get(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write information to this file", e);
        }
    }
}


