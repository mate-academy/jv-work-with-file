package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeDateToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(NEW_LINE);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        StringBuilder builder = new StringBuilder();
        String[] line = data.split(NEW_LINE);
        int supplySum = 0;
        int buySum = 0;
        for (String lines : line) {
            String[] parts = lines.split(",");
            if (parts[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(parts[AMOUNT_INDEX]);
            } else if (parts[OPERATION_TYPE_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(parts[AMOUNT_INDEX]);
            }
        }
        builder.append(SUPPLY).append(",").append(supplySum)
                .append(NEW_LINE)
                .append(BUY).append(",").append(buySum)
                .append(NEW_LINE)
                .append(RESULT)
                .append(",").append(supplySum - buySum);
        return builder.toString();
    }

    private void writeDateToFile(String toFile, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFile, e);
        }
    }
}
