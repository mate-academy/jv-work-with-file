package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final int EMPTY_ARRAY = 0;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private String[] readFromFile(String fromFileName) {
        List<String> list;
        try {
            list = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file: " + fromFileName, e);
        }
        return list.toArray(new String[EMPTY_ARRAY]);
    }

    private StringBuilder createReport(String[] fromFileArray) {
        int counterSupply = 0;
        int counterBuy = 0;
        for (String fromFileArrayLine : fromFileArray) {
            if (fromFileArrayLine.split(SEPARATOR)[OPERATION_TYPE].equals(SUPPLY)) {
                counterSupply += Integer.parseInt(fromFileArrayLine.split(SEPARATOR)[AMOUNT]);
            } else {
                counterBuy += Integer.parseInt(fromFileArrayLine.split(SEPARATOR)[AMOUNT]);
            }
        }
        int result = counterSupply - counterBuy;
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(SEPARATOR).append(counterSupply)
                .append(System.lineSeparator()).append(BUY).append(SEPARATOR).append(counterBuy)
                .append(System.lineSeparator()).append(RESULT).append(SEPARATOR).append(result);
    }

    private void writeToFile(String toFileName, StringBuilder report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.append(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file; " + toFileName, e);
        }
    }
}
