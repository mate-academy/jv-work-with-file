package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readData(fromFileName));
        writeData(toFileName, report);
    }

    private String readData(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return builder.toString();
    }

    private String createReport(String string) {
        String[] lines = string.split(System.lineSeparator());
        int buyTotal = 0;
        int supplyTotal = 0;
        for (String line : lines) {
            if (line.split(COMMA)[OPERATION_INDEX].equals(BUY)) {
                buyTotal += Integer.parseInt(line.split(COMMA)[AMOUNT_INDEX]);
            } else {
                supplyTotal += Integer.parseInt(line.split(COMMA)[AMOUNT_INDEX]);
            }
        }
        int result = supplyTotal - buyTotal;
        return createResultString(supplyTotal, buyTotal, result);
    }

    private String createResultString(int supplyTotal, int buyTotal, int result) {
        return (new StringBuilder(SUPPLY).append(COMMA).append(supplyTotal)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyTotal).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString());
    }

    private void writeData(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into file", e);
        }
    }
}
