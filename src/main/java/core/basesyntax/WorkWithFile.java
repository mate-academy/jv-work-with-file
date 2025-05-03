package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final String SUPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final byte FIRST_ELEMENT_IN_ARRAY = 0;
    private static final byte SECOND_ELEMENT_IN_ARRAY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        String text = readInfo(fromFile);
        String statistic = getStatisticFromText(text);
        writeInfo(toFile, statistic);
    }

    private String getStatisticFromText(String text) {
        int supplySum = 0;
        int buySum = 0;
        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            String[] data = line.split(COMA);
            if (data[FIRST_ELEMENT_IN_ARRAY].equals(SUPLY)) {
                supplySum += Integer.parseInt(data[SECOND_ELEMENT_IN_ARRAY]);
                continue;
            }
            buySum += Integer.parseInt(data[SECOND_ELEMENT_IN_ARRAY]);
        }

        return new StringBuilder().append(SUPLY).append(COMA)
                .append(supplySum).append(System.lineSeparator()).append(BUY)
                .append(COMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(supplySum - buySum).toString();
    }

    private String readInfo(File fromFile) {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            reader.lines().forEach(s -> result.append(s).append(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fromFile, e);
        }

        return result.toString();
    }

    private void writeInfo(File toFile, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into to file: " + toFile, e);
        }
    }
}
