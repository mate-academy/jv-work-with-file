package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public String createReport(int supplySum, int buySum) {
        StringBuilder reportBuilder = new StringBuilder();
        return String.valueOf(reportBuilder.append("supply,").append(supplySum)
                .append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum));
    }

    public void writeToFile(String toFileName, String data) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't write data", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[OPERATION_TYPE_INDEX].equals("supply")) {
                    supplySum = supplySum + Integer.parseInt(split[AMOUNT_INDEX]);
                } else if (split[OPERATION_TYPE_INDEX].equals("buy")) {
                    buySum = buySum + Integer.parseInt(split[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
            String result = createReport(supplySum, buySum);
            writeToFile(toFileName, result);
        } catch (Exception e) {
            throw new RuntimeException("Cant read file", e);
        }
    }
}
