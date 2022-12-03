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
    private static final String RESULT = "result";
    private static final String CSV_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final byte OP_TYPE_INDEX = 0;
    private static final byte AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileReadFrom = new File(fromFileName);
        File fileWriteTo = new File(toFileName);
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReadFrom))) {
            String csvLine;
            String[] values;
            while ((csvLine = bufferedReader.readLine()) != null) {
                values = csvLine.split(CSV_SEPARATOR);
                if (values[OP_TYPE_INDEX].equals(SUPPLY)) {
                    totalSupply += Integer.parseInt(values[AMOUNT_INDEX]);
                    continue;
                }
                totalBuy += Integer.parseInt(values[AMOUNT_INDEX]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fromFileName, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWriteTo))) {
            bufferedWriter.write(getReport(totalSupply, totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    private static String getReport(int totalSupply, int totalBuy) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(SUPPLY).append(CSV_SEPARATOR).append(totalSupply).append(LINE_SEPARATOR)
                .append(BUY).append(CSV_SEPARATOR).append(totalBuy).append(LINE_SEPARATOR)
                .append(RESULT).append(CSV_SEPARATOR).append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }
}
