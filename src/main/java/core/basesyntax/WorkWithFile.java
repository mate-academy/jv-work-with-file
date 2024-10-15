package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;
    private static final int TYPE_INDEX = 0;
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String text = createReport(fromFileName);
        writeReportToFile(toFileName, text);
    }

    private int[] readFile(String fromFile) {
        int[] result = new int[3];
        int supplyAmount = 0;
        int buyAmount = 0;
        int resultAmount;
        try (BufferedReader reader
                     = new BufferedReader(new FileReader(fromFile))) {
            String data = reader.readLine();
            while (data != null) {
                String[] line = data.split(COMMA);
                if (line[TYPE_INDEX].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(line[AMOUNT_INDEX]);
                } else {
                    buyAmount += Integer.parseInt(line[AMOUNT_INDEX]);
                }
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFile, e);
        }
        resultAmount = supplyAmount - buyAmount;
        result[0] = supplyAmount;
        result[1] = buyAmount;
        result[2] = resultAmount;
        return result;
    }

    private String createReport(String fromFile) {
        int[] data = readFile(fromFile);
        int supplyAmount = data[0];
        int buyAmount = data[1];
        int resultAmount = data[2];

        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder.append(SUPPLY).append(COMMA)
                .append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(resultAmount).toString();

    }

    private void writeReportToFile(String toFile, String text) {
        try (BufferedWriter writer
                     = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFile, e);
        }
    }
}
