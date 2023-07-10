package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeReportToFile(toFileName, createReport(readDataFromFile(fromFileName)));
    }

    private String [] readDataFromFile(String fromFileName) {
        String text;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            StringBuilder readDataFromFile = new StringBuilder();
            while (value != null) {
                readDataFromFile.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            text = readDataFromFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return text.split(System.lineSeparator());
    }

    private String createReport(String [] text) {
        int supplyTotal = 0;
        int buyTotal = 0;
        StringBuilder report = new StringBuilder();
        for (String line : text) {
            String[] fields = line.split(COMMA);
            if (fields[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(fields[AMOUNT_INDEX]);
            } else if (fields[OPERATION_TYPE_INDEX].equals(BUY)) {
                buyTotal += Integer.parseInt(fields[AMOUNT_INDEX]);
            }
        }
        int result = supplyTotal - buyTotal;
        return report.append(SUPPLY).append(COMMA).append(supplyTotal)
                           .append(System.lineSeparator())
                           .append(BUY).append(COMMA).append(buyTotal)
                           .append(System.lineSeparator())
                           .append(RESULT).append(COMMA).append(result)
                           .append(System.lineSeparator()).toString();
    }

    private void writeReportToFile(String toFileName, String createReport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(createReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}



