package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private List<String> readFromFile(String fromMyFileName) {
        File fromMyFile = new File(fromMyFileName);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fromMyFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String value = null;

        try {
            value = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> list = new ArrayList<>();
        while (value != null) {
            list.add(value);
            try {
                value = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    private String createReport(List<String> readFromFile) {
        StringBuilder resultBuilder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int resultSum;

        for (String result : readFromFile) {
            if (result != null) {
                if (result.startsWith(SUPPLY)) {
                    supplySum += Integer.parseInt(result.substring(SUPPLY.length() + 1));
                }
                if (result.startsWith(BUY)) {
                    buySum += Integer.parseInt(result.substring(BUY.length() + 1));
                }
            }
        }
        resultSum = supplySum - buySum;
        return resultBuilder
                .append(SUPPLY).append(SEPARATOR)
                .append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum)
                .append(System.lineSeparator()).append(RESULT).append(SEPARATOR)
                .append(resultSum).toString();
    }

    private void writeToFile(String report, String toFileName) {
        File toMyFile = new File(toFileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(toMyFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try (bufferedWriter) {

            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
