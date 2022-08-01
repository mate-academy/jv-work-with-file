package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final String RESULT = "result";
    private static final String COMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFileByLines(fromFileName);
        String statistic = countStatistic(lines);
        writeStatisticToFile(toFileName,statistic);
    }

    private void writeStatisticToFile(String toFileName, String statistic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private String countStatistic(String[] strings) {
        int buyAmount = 0;
        int supplyAmount = 0;
        for (String string : strings) {
            String[] operation = string.split(COMA_SEPARATOR);
            if (SUPPLY_OPERATION_TYPE.equals(operation[0])) {
                supplyAmount += Integer.parseInt(operation[1]);
            } else {
                buyAmount += Integer.parseInt(operation[1]);
            }
        }
        int resultAmount = supplyAmount - buyAmount;
        return new StringBuilder()
                .append(SUPPLY_OPERATION_TYPE).append(COMA_SEPARATOR)
                .append(supplyAmount).append(System.lineSeparator())
                .append(BUY_OPERATION_TYPE).append(COMA_SEPARATOR)
                .append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(COMA_SEPARATOR).append(resultAmount)
                .toString();
    }

    private String[] readFileByLines(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }
}
