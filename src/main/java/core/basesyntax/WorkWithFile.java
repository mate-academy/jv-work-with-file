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
    private static final String CHARACTER = ",";
    private static final String RESULT = "result";

    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    private String readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        int buyTotal = 0;
        int supplyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] splitArray = line.split(CHARACTER);
            String operation = splitArray[OPERATION_INDEX];
            int amount = Integer.parseInt(splitArray[AMOUNT_INDEX]);

            if (operation.equals(BUY)) {
                buyTotal += amount;
            } else if (operation.equals(SUPPLY)) {
                supplyTotal += amount;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(CHARACTER).append(supplyTotal).append(System.lineSeparator());
        builder.append(BUY).append(CHARACTER).append(buyTotal).append(System.lineSeparator());
        builder.append(RESULT).append(CHARACTER).append(supplyTotal - buyTotal);

        return builder.toString();
    }

    private void writeReportToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }
}
