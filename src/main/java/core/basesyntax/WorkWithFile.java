package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        String[] records = readFromFile(fromFileName).split(System.lineSeparator());

        for (String record : records) {
            if (record.contains(SUPPLY)) {
                totalSupply += Integer.parseInt(record.substring(record.indexOf(COMMA) + 1));
            }
            if (record.contains(BUY)) {
                totalBuy += Integer.parseInt(record.substring(record.indexOf(COMMA) + 1));
            }
        }
        StringBuilder builderReport = createReport(totalSupply, totalBuy);
        writeToFile(toFileName, builderReport);
    }

    private void writeToFile(String fileName, StringBuilder stringBuilder) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File(fileName)))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private StringBuilder createReport(int totalSupply, int totalBuy) {
        return new StringBuilder().append(SUPPLY).append(COMMA).append(totalSupply)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(totalBuy).append(System.lineSeparator()).append(RESULT).append(COMMA)
                .append(totalSupply - totalBuy).append(System.lineSeparator());
    }
}
