package core.basesyntax;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class WorkWithFile {
    private static final String BUY_INDEX = "buy";
    private static final String SUPPLY_INDEX = "supply";
    private static final String RESULT_INDEX = "result";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String SPACE_SEPARATOR = " ";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeToFile(toFileName);
    }

    public String[] readFile(String fromFileName) {
        StringBuilder resultBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader
                    .readLine();
            while (String.valueOf(value) != null) {
                resultBuilder
                        .append(value)
                        .append(SPACE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read to file", e);
        }
        return resultBuilder
                .toString()
                .split(SPACE_SEPARATOR);
    }

    private String generateReport(String[] fromFileDate) {
        int buyCount = 0;
        int supplyCount = 0;
        for (String line: fromFileDate) {
            String[] words = line.split(COMMA_SEPARATOR);

        }
    }
 }