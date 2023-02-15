package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPES_RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        printToFile(toFileName, getReport(fromFileName));
    }

    private String getReport(String fileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] data = readFromFile(fileName).split(System.lineSeparator());
        StringBuilder reportBuilder = new StringBuilder();
        for (String datum : data) {
            String[] datumInfo = datum.split(COMMA);
            if (datumInfo[0].equals(OPERATION_TYPE_SUPPLY)) {
                supplyAmount += Integer.parseInt(datumInfo[1]);
            } else {
                buyAmount += Integer.parseInt(datumInfo[1]);
            }
        }
        reportBuilder.append(OPERATION_TYPE_SUPPLY).append(COMMA)
                .append(supplyAmount).append(System.lineSeparator())
                .append(OPERATION_TYPE_BUY).append(COMMA)
                .append(buyAmount).append(System.lineSeparator())
                .append(OPERATION_TYPES_RESULT).append(COMMA)
                .append(supplyAmount - buyAmount);
        return reportBuilder.toString();
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return builder.toString();
    }

    private void printToFile(String fileName, String data) {
        try {
            Files.write(Path.of(fileName), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}
