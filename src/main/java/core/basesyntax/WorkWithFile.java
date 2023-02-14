package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] data = readFromFile(fromFileName).split(System.lineSeparator());
        StringBuilder resultBuilder = new StringBuilder();
        for (String datum : data) {
            String[] result = datum.split(COMMA);
            if (datum.startsWith(OPERATION_TYPE_SUPPLY)) {
                supplyAmount += Integer.parseInt(result[1]);
            } else {
                buyAmount += Integer.parseInt(result[1]);
            }
        }
        resultBuilder.append(OPERATION_TYPE_SUPPLY).append(COMMA)
                .append(supplyAmount).append(System.lineSeparator())
                .append(OPERATION_TYPE_BUY).append(COMMA)
                .append(buyAmount).append(System.lineSeparator())
                .append("result").append(COMMA)
                .append(supplyAmount - buyAmount);
        printToFile(toFileName, resultBuilder.toString());
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
