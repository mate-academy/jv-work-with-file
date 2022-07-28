package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY_CONDITION = "supply";
    private static final String BUY_CONDITION = "buy";
    private static final String REGEX_CONDITION = "\\D";
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readDataFromFile(fromFileName);
        int[] array = calculateResultForDay(textFromFile);
        String result = toStringForNewFile(array);
        writeToFile(toFileName, result);

    }

    //прочитал файл и отдал стринг билдер
    private String readDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = null;
            try {
                value = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read file " + e);
            }
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                try {
                    value = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write file in StringBuilder " + e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + e);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + e);
        }
        return stringBuilder.toString();
    }

    public int[] calculateResultForDay(String fromFileNam) {
        int result = 0;
        int numberFromLine = 0;
        String[] sortedArray = fromFileNam.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String line : sortedArray) {
            sortedArray = line.split(" ");
            if (line.length() > 1) {
                numberFromLine = Integer.parseInt(line.replaceAll(REGEX_CONDITION, ""));
            }
            if (sortedArray[0].contains(SUPPLY_CONDITION)) {
                supplySum += numberFromLine;
            }
            if (sortedArray[0].contains(BUY_CONDITION)) {
                buySum += numberFromLine;
            }
            result = supplySum - buySum;
        }
        return new int[]{buySum, supplySum, result};
    }

    public String toStringForNewFile(int[] results) {
        StringBuilder sbtoString = new StringBuilder();
        return sbtoString.append("supply,").append(results[1]).append(System.lineSeparator())
                .append("buy,").append(results[0]).append(System.lineSeparator())
                .append("result,").append(results[2]).append(System.lineSeparator()).toString();

    }

    public void writeToFile(String toFileName, String string) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + e);
        }
        try {
            Files.write(file.toPath(), string.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e);

        }
    }

}
