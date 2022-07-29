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

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private String[] readFromFile(String fromFileName) {
        List<String> dataFromFile;
        try {
            dataFromFile = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file: " + fromFileName, e);
        }
        return dataFromFile.toArray(new String[EMPTY_ARRAY]);
    }

    private String createReport(String[] fileRows) {
        int counterSupply = 0;
        int counterBuy = 0;
        for (String row : fileRows) {
            if (row.split(SEPARATOR)[OPERATION_TYPE].equals("supply")) {
                counterSupply += Integer.parseInt(row.split(SEPARATOR)[AMOUNT]);
            } else {
                counterBuy += Integer.parseInt(row.split(SEPARATOR)[AMOUNT]);
            }
        }
        int result = counterSupply - counterBuy;
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(counterSupply)
                .append(System.lineSeparator()).append("buy,").append(counterBuy)
                .append(System.lineSeparator()).append("result,").append(result).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.append(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file; " + toFileName, e);
        }
    }
}
