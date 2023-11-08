package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DATA_SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int SUM_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readingFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readingFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value).append(LINE_SEPARATOR);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        String[] splitLine = data.split(LINE_SEPARATOR);
        int totalSupply = 0;
        int totalBuy = 0;

        for (String value : splitLine) {
            String[] splitData = value.split(DATA_SEPARATOR);
            if (splitData[OPERATION_INDEX].equals(SUPPLY)) {
                try {
                    totalSupply += Integer.parseInt(splitData[SUM_INDEX]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Number Format is wrong", e);
                }
            } else {
                try {
                    totalBuy += Integer.parseInt(splitData[SUM_INDEX]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Number Format is wrong", e);
                }
            }
        }

        return SUPPLY + DATA_SEPARATOR + totalSupply + LINE_SEPARATOR
                + BUY + DATA_SEPARATOR + totalBuy + LINE_SEPARATOR
                + "result" + DATA_SEPARATOR + (totalSupply - totalBuy);
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
