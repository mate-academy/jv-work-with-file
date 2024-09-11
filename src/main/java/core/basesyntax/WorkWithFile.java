package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] result = readFile(fromFileName);
        String find = getResult(result);
        writeToFile(find, toFileName);
    }

    private int[] readFile(String sourceFile) {
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;
        String variable;

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String value = reader.readLine();
            while (value != null) {
                variable = value.substring(0, value.indexOf(COMMA));
                String amount = value.substring(value.indexOf(COMMA) + 1);
                if (variable.equals(SUPPLY)) {
                    supplyResult += Integer.parseInt(amount);
                } else if (variable.equals(BUY)) {
                    buyResult += Integer.parseInt(amount);
                }
                result = supplyResult - buyResult;
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the data from the file " + sourceFile, e);
        }
        return new int[] {supplyResult, buyResult, result};
    }

    private String getResult(int[] data) {
        int supplyResult = data[0];
        int buyResult = data[1];
        int result = data[2];
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA)
                .append(supplyResult).append(System.lineSeparator())
                .append(BUY).append(COMMA)
                .append(buyResult).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }

    private void writeToFile(String data, String resultFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into the file" + resultFile, e);
        }
    }
}
