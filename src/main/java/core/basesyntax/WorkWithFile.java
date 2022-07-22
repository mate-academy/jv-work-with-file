package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToReport(toFileName, createReport(readFromFile(fromFileName)));
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File " + fromFileName + " cannot be read", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String line) {
        int sumBuy = 0;
        int sumSupply = 0;
        StringBuilder reportBuilder = new StringBuilder();
        String[] splittedLine = line.split(SEPARATOR);
        for (int i = 0; i < splittedLine.length; i += 2) {
            if (splittedLine[i].equals(BUY)) {
                sumBuy += Integer.parseInt(splittedLine[i + 1]);
            }
            if (splittedLine[i].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(splittedLine[i + 1]);
            }
        }
        int result = sumSupply - sumBuy;
        reportBuilder.append(SUPPLY).append(SEPARATOR).append(sumSupply)
                .append(System.lineSeparator()).append(BUY).append(SEPARATOR)
                .append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result);
        return reportBuilder.toString();
    }

    public void writeToReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File " + toFileName + " cannot be write", e);
        }
    }
}
