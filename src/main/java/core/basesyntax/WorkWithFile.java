package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String SUPPL_YOPERATION = "supply";
    private final String BUY_OPERATION = "buy";
    private final String DATA_SEPARATOR = " ";
    private final String CSV_SEPARATOR = ",";
    private final String RESULT = "result";
    private final int COLLUMN_INDEX_0 = 0;
    private final int COLLUMN_INDEX_1 = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(report(readFile(fromFileName)), toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file...", e);
        }
        return stringBuilder.toString();
    }

    public String report (String resultReport) {
        int supplySum = 0;
        int buySum = 0;
        String[] dataFromFileArray = resultReport.split(DATA_SEPARATOR);
        for (String s : dataFromFileArray) {
            String[] values = s.split(CSV_SEPARATOR);
            if (values[COLLUMN_INDEX_0].equals(SUPPL_YOPERATION)) {
                supplySum += Integer.parseInt(values[COLLUMN_INDEX_1]);
            } else {
                buySum += Integer.parseInt(values[COLLUMN_INDEX_1]);
            }
        }
        return new StringBuilder().append(SUPPL_YOPERATION).append(CSV_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(CSV_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(CSV_SEPARATOR).append(supplySum - buySum)
                .toString();
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write this data...", e);
        }
    }
}
