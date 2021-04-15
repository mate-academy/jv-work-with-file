package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String DATA_SEPARATOR = " ";
    public static final String CSV_SEPARATOR = ",";
    public static final String SUPPLY_OPERATION = "supply";
    public static final String BUY_OPERATION = "buy";
    public static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeReportToFile(createReport(getDataFromFile(fromFileName)), toFileName);
    }

    private String getDataFromFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(DATA_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file...", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        int supplySum = 0;
        int buySum = 0;
        String[] dataFromFileArray = dataFromFile.split(DATA_SEPARATOR);
        for (String s : dataFromFileArray) {
            String[] values = s.split(CSV_SEPARATOR);
            if (values[0].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(values[1]);
            } else {
                buySum += Integer.parseInt(values[1]);
            }
        }
        return new StringBuilder().append(SUPPLY_OPERATION).append(CSV_SEPARATOR).append(supplySum)
                    .append(System.lineSeparator())
                    .append(BUY_OPERATION).append(CSV_SEPARATOR).append(buySum)
                    .append(System.lineSeparator())
                    .append(RESULT_OPERATION).append(CSV_SEPARATOR).append(supplySum - buySum)
                    .toString();
    }

    private void writeReportToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write this data...", e);
        }
    }
}
