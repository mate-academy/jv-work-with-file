package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readDataFromFile(fromFileName);
        int[] array = calculateResultForDay(textFromFile);
        String result = toStringForNewFile(array);
        writeToFile(toFileName, result);
    }

    private String readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + fromFileName + e);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName + e);
        }
        return stringBuilder.toString();
    }

    private int[] calculateResultForDay(String text) {
        final String supply_condition = "supply";
        final String
                buy_condition = "buy";
        final String regex_condition = "\\D";
        String[] allLines = text.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String line : allLines) {
            allLines = line.split(",");
            if (allLines [0].contains(supply_condition)) {
                supplySum += Integer.parseInt(line.replaceAll(regex_condition, ""));
            }
            if (allLines [0].contains(buy_condition)) {
                buySum += Integer.parseInt(line.replaceAll(regex_condition, ""));
            }
        }
        return new int[]{buySum, supplySum, supplySum - buySum};
    }

    private String toStringForNewFile(int[] results) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(results[1]).append(System.lineSeparator())
                .append("buy,").append(results[0]).append(System.lineSeparator())
                .append("result,").append(results[2]).append(System.lineSeparator()).toString();
    }

    private void writeToFile(String toFileName, String string) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + toFileName + e);
        }
        try {
            Files.write(file.toPath(), string.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName + e);
        }
    }
}
