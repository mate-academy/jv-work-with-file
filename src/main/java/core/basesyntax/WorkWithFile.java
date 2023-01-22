package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String SUPPLY_OPERATION = "supply";
    public static final String BUY_OPERATION = "buy";
    public static final String RESULT = "result";
    public static final String DATA_SEPARATOR = " ";
    public static final String CSV_SEPARATOR = ",";
    public static final int OPERATION_INDEX_0 = 0;
    public static final int AMOUNT_INDEX_1 = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFile(fromFileName)), toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader =
                         new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(DATA_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String resultReport) {
        int supplySum = 0;
        int buySum = 0;
        String[] dataFromFileArray = resultReport.split(DATA_SEPARATOR);
        for (String var : dataFromFileArray) {
            String[] values = var.split(CSV_SEPARATOR);
            if (values[OPERATION_INDEX_0].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(values[AMOUNT_INDEX_1]);
            } else {
                buySum += Integer.parseInt(values[AMOUNT_INDEX_1]);
            }
        }

        return new StringBuilder().append(SUPPLY_OPERATION).append(CSV_SEPARATOR)
                        .append(supplySum).append(System.lineSeparator())
                        .append(BUY_OPERATION).append(CSV_SEPARATOR).append(buySum)
                        .append(System.lineSeparator())
                        .append(RESULT).append(CSV_SEPARATOR).append(supplySum - buySum)
                        .toString();
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
