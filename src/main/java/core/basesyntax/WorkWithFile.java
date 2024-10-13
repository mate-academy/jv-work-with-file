package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFile(fromFileName);
        writeFile(toFileName, content);
    }

    private String readFile(String fileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(",");
                if (info[OPERATION_TYPE].equals(BUY)) {
                    buyAmount += Integer.parseInt(info[AMOUNT]);
                } else if (info[OPERATION_TYPE].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(info[AMOUNT]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
        int resultAmount = supplyAmount - buyAmount;

        StringBuilder result = new StringBuilder()
                .append(SUPPLY).append(",").append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(",").append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(",").append(resultAmount);

        return result.toString();
    }

    private void writeFile(String fileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + fileName, e);
        }
    }
}
