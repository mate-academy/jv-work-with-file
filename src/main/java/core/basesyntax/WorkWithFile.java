package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String REGEX = ",";

    private int supplySum;
    private int buySum;

    public void readFromFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                    supplySum = supplySum + Integer.parseInt(split[AMOUNT_INDEX]);
                } else if (split[OPERATION_TYPE_INDEX].equals(BUY)) {
                    buySum = buySum + Integer.parseInt(split[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Cant read file", e);
        }
    }

    public String createReport(int supplySum, int buySum) {
        StringBuilder reportBuilder = new StringBuilder();
        return String.valueOf(reportBuilder.append(SUPPLY).append(REGEX).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(REGEX).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(REGEX).append(supplySum - buySum));
    }

    public void writeToFile(String toFileName, String report) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't write data", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        String report = createReport(supplySum, buySum);
        writeToFile(toFileName, report);
    }
}
