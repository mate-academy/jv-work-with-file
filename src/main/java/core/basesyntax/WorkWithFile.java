package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String DIVIDER = ",";
    private static final String OPPERATION_SUPPLY = "supply";
    private static final String OPPERATION_BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_NAME = 0;
    private static final int OPERATION_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = prepareReport(data);
        writeToFile(toFileName, report);
    }

    public String readFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Problem with reading file" + fileName, e);
        }
    }

    public String prepareReport(String data) {
        String[] dataLine = data.split(System.lineSeparator());
        int supplyTotal = 0;
        int buyTotal = 0;
        int result;
        StringBuilder report = new StringBuilder();
        for (String line : dataLine) {
            String[] text = line.split(DIVIDER);
            if (text[OPERATION_NAME].equals(OPPERATION_SUPPLY)) {
                supplyTotal += Integer.parseInt(text[OPERATION_VALUE]);
            } else if (text[OPERATION_NAME].equals(OPPERATION_BUY)) {
                buyTotal += Integer.parseInt(text[OPERATION_VALUE]);
            }
        }
        result = supplyTotal - buyTotal;
        report.append(OPPERATION_SUPPLY).append(DIVIDER).append(supplyTotal)
                .append(System.lineSeparator())
                .append(OPPERATION_BUY).append(DIVIDER).append(buyTotal)
                .append(System.lineSeparator())
                .append(RESULT).append(DIVIDER).append(result);
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Problem with writing file" + e);
        }
    }
}
