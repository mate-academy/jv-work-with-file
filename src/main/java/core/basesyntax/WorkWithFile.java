package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    static final String COMMA_SEPARATOR = ",";
    static final int OPERATION_TYPE = 0;
    static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringList = readFile(fromFileName);
        String report = (createReport(stringList));
        writeToFile(report, toFileName);
    }

    private List<String> readFile(String fromFileName) throws RuntimeException {
        List<String> fromFileToList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = reader.readLine();
            while (readLine != null) {
                fromFileToList.add(readLine);
                readLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fromFileToList;
    }

    private String createReport(List<String> fromFileToList) {
        int amountOfSupplies = 0;
        int amountOfPurchases = 0;
        StringBuilder reportBuilder = new StringBuilder();
        for (String value : fromFileToList) {
            String[] split = value.split(COMMA_SEPARATOR);
            int amount = Integer.parseInt(split[AMOUNT]);
            if (split[OPERATION_TYPE].equals("supply")) {
                amountOfSupplies += amount;
            } else {
                amountOfPurchases += amount;
            }
        }
        reportBuilder.append("supply")
                    .append(COMMA_SEPARATOR)
                    .append(amountOfSupplies)
                    .append(System.lineSeparator())
                    .append("buy")
                    .append(COMMA_SEPARATOR)
                    .append(amountOfPurchases)
                    .append(System.lineSeparator())
                    .append("result")
                    .append(COMMA_SEPARATOR)
                    .append(amountOfSupplies - amountOfPurchases);
        return reportBuilder.toString();
    }

    private void writeToFile(String data, String toFileName)
            throws RuntimeException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file", e);
        }
    }
}

