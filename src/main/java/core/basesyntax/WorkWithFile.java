package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY_CONDITION = "supply";
    private static final String BUY_CONDITION = "buy";
    private static final String REGEX_CONDITION = "\\D";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readDataFromFile(fromFileName);
        int[] array = calculateResultForDay(textFromFile);
        String result = createReport(array);
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
        String[] allLines = text.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String line : allLines) {
            allLines = line.split(COMMA);
            if (allLines[0].contains(SUPPLY_CONDITION)) {
                supplySum += Integer.parseInt(line.replaceAll(REGEX_CONDITION, ""));
            }
            if (allLines[0].contains(BUY_CONDITION)) {
                buySum += Integer.parseInt(line.replaceAll(REGEX_CONDITION, ""));
            }
        }
        return new int[]{buySum, supplySum, supplySum - buySum};
    }

    private String createReport(int[] results) {
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
