package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR_SYMBOL = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int SUM_INDEX = 1;
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(fromFileName), toFileName);
    }

    private String createReport(String fileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] line = value.split(SEPARATOR_SYMBOL);
                if (line[OPERATION_INDEX].equals(SUPPLY_STRING)) {
                    supplySum += Integer.parseInt(line[SUM_INDEX]);
                }
                if (line[OPERATION_INDEX].equals(BUY_STRING)) {
                    buySum += Integer.parseInt(line[SUM_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file with name: " + fileName, e);
        }
        return new StringBuilder(SUPPLY_STRING)
                .append(SEPARATOR_SYMBOL).append(supplySum)
                .append(System.lineSeparator()).append(BUY_STRING)
                .append(SEPARATOR_SYMBOL).append(buySum)
                .append(System.lineSeparator()).append("result")
                .append(SEPARATOR_SYMBOL).append(supplySum - buySum).toString();
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can’t write data to file", e);
        }
    }
}
