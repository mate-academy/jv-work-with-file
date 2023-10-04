package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] rows = readLines(fromFileName);
        StringBuilder stringBuilder = createReport(rows);
        saveReport(toFileName, stringBuilder.toString());
    }

    private void saveReport(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private StringBuilder createReport(String[] rows) {
        int sumSupply = 0;
        int sumBuy = 0;
        for (String row : rows) {
            String[] elements = row.split(COMMA);
            if (elements[0].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(elements[1]);
            }
            if (elements[0].equals(BUY)) {
                sumBuy += Integer.parseInt(elements[1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append((sumSupply - sumBuy));
        return stringBuilder;
    }

    private String[] readLines(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return builder.toString().split(System.lineSeparator());
        } catch (Exception e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }
}
