package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_ZERO = 0;

    private static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readFromFile(fromFileName);
        String countOfData = count(linesFromFile);
        writeToFile(countOfData, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String lines;
            while ((lines = reader.readLine()) != null) {
                builder.append(lines).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return builder.toString().split(" ");
    }

    private String count(String[] linesFromFile) {
        int valueBuy = 0;
        int valueSupply = 0;
        for (String lineFromFile : linesFromFile) {
            String[] splitLine = lineFromFile.split(",");
            String operationType = splitLine[INDEX_ZERO];
            int sum = Integer.parseInt(splitLine[INDEX_ONE]);
            if (operationType.equals("buy")) {
                valueBuy += sum;
            }
            if (operationType.equals("supply")) {
                valueSupply += sum;
            }
        }
        return reportOfData(valueBuy, valueSupply);
    }

    private String reportOfData(int valueBuy, int valueSupply) {
        return "supply," + valueSupply + System.lineSeparator()
                + "buy," + valueBuy + System.lineSeparator()
                + "result," + (valueSupply - valueBuy);
    }

    private void writeToFile(String countOfData, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(countOfData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
