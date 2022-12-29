package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    static final String COMMA_SEPARATOR = ",";
    static final int OPERATION_TYPE = 0;
    static final int AMOUNT = 1;
    static final String SUPPLY = "supply";
    static final String BUY = "buy";
    static final String RESULT = "result";
    static final int ZERO = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringList = readFile(fromFileName);
        StringBuilder report = createReport(stringList, toFileName);
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

    private StringBuilder createReport(List<String> fromFileToList, String toFileName) {
        int amountOfSupplies = ZERO;
        int amountOfPurchases = ZERO;
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : fromFileToList) {
            String[] split = value.split(COMMA_SEPARATOR);
            int amount = Integer.parseInt(split[AMOUNT]);
            if (split[OPERATION_TYPE].equals(SUPPLY)) {
                amountOfSupplies += amount;
            } else {
                amountOfPurchases += amount;
            }
        }
        File file = new File(toFileName);
        if (file.length() == ZERO) {
            stringBuilder.append(SUPPLY)
                    .append(COMMA_SEPARATOR)
                    .append(amountOfSupplies)
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(COMMA_SEPARATOR)
                    .append(amountOfPurchases)
                    .append(System.lineSeparator())
                    .append(RESULT)
                    .append(COMMA_SEPARATOR)
                    .append(amountOfSupplies - amountOfPurchases);
        }
        return stringBuilder;
    }

    private void writeToFile(StringBuilder stringBuilder, String toFileName)
            throws RuntimeException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file", e);
        }
    }
}

