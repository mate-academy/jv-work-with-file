package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String STRING_SEPARATOR = ",";
    private static final String[] REPORT_COLUMNS = new String[] {"supply", "buy", "result"};

    public void getStatistic(String fromFileName, String toFileName) {
        clearFile(toFileName);
        writeFile(fromFileName, toFileName);
    }

    private void writeFile(String fromFileName, String toFileName) {
        HashMap<String, Integer> results = calculateStatisticResults(fromFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String column: REPORT_COLUMNS) {
                bufferedWriter.write(column + "," + results.get(column) + System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName + ". Error: " + e);
        }
    }

    private HashMap<String, Integer> calculateStatisticResults(String fromFileName) {
        HashMap<String, Integer> results = new HashMap<>();
        File file = new File(fromFileName);

        try {
            List<String> lines = Files.readAllLines(file.toPath());

            for (String line: lines) {
                String[] splitLine = line.split(STRING_SEPARATOR);
                String operationType = splitLine[0];
                int amount = Integer.parseInt(splitLine[1]);

                Integer previousSum = results.get(operationType);

                if (previousSum != null) {
                    results.replace(operationType, previousSum + amount);
                } else {
                    results.put(operationType, amount);
                }
            }

            Integer totalSupply = results.get(REPORT_COLUMNS[0]);
            Integer totalBuy = results.get(REPORT_COLUMNS[1]);
            results.put(REPORT_COLUMNS[2], totalSupply - totalBuy);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: ", e);
        }

        return results;
    }

    private void clearFile(String toFileName) {
        File file = new File(toFileName);

        if (file.delete()) {
            System.out.println("File: " + file.getName() + " successful deleted.");
        }
    }

}
