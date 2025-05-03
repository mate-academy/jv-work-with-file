package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        String[] data;
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file " + fromFileName, e);
        }
        data = stringBuilder.toString().split(System.lineSeparator());
        return data;
    }

    private String createReport(String[] data) {
        int supplyInt = 0;
        int buyInt = 0;
        int resultInt = 0;
        int quantity = 0;
        for (String line : data) {
            String[] dataUnits = line.split(COMA);
            quantity = Integer.parseInt(dataUnits[AMOUNT_INDEX]);
            if (dataUnits[OPERATION_INDEX].equals(SUPPLY)) {
                supplyInt += quantity;
            } else {
                buyInt += quantity;
            }
        }
        resultInt = supplyInt - buyInt;
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(COMA)
                .append(supplyInt)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMA)
                .append(buyInt)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMA).append(resultInt)
                .append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file " + toFileName,e);
        }
    }
}
