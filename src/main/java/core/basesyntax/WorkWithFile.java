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
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] output = readFile(fromFileName);
        String result = getResult(output);
        writeToFile(result, toFileName);
    }

    private int[] readFile(String sourceFile) {
        File file = new File(sourceFile);
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.contains(SUPPLY)) {
                    supplyResult += Integer.parseInt(value.substring(value.indexOf(COMMA) + 1));
                }
                if (value.contains(BUY)) {
                    buyResult += Integer.parseInt(value.substring(value.indexOf(COMMA) + 1));
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
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(data[0]).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(data[1]).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(data[2]);
        return builder.toString();
    }

    private void writeToFile(String data, String resultFile) {
        File newFile = new File(resultFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into the file" + resultFile, e);
        }
    }
}
