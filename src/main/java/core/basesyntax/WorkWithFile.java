package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_ACTION = 0;
    private static final int INDEX_OF_NUMBERS = 1;
    private static final String ACTION_SUPPLY = "supply";
    private static final String ACTION_BUY = "buy";

    private String[] readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fileName, e);
        }
        return stringBuilder.toString().trim().split(" ");
    }

    private String createReport(String[] info) {
        StringBuilder stringBuilder = new StringBuilder();
        int sumSupply = 0;
        int sumBuy = 0;

        for (String str : info) {
            String[] line = str.split(",");
            if (line[INDEX_OF_ACTION].equals(ACTION_SUPPLY)) {
                sumSupply += Integer.valueOf(line[INDEX_OF_NUMBERS]);
            } else if (line[INDEX_OF_ACTION].equals(ACTION_BUY)) {
                sumBuy += Integer.valueOf(line[INDEX_OF_NUMBERS]);
            }
        }
        return stringBuilder.append("supply,").append(sumSupply)
                .append(System.lineSeparator())
                .append("buy,").append(sumBuy)
                .append(System.lineSeparator())
                .append("result,").append(sumSupply - sumBuy).toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readFile(fromFileName));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file" + toFileName, e);
        }
    }
}
