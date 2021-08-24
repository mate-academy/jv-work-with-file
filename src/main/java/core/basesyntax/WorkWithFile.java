package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String resultString = readSourceFile(fromFileName);
        writeResultToFile(resultString, toFileName);
    }

    private String readSourceFile(String fromFileName) {
        int buyAmount = 0;
        int supplyAmount = 0;
        StringBuilder resultBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] words = line.split(",");
                if (words[OPERATION_TYPE_INDEX].equals(BUY)) {
                    buyAmount += Integer.parseInt(words[AMOUNT_INDEX]);
                }
                if (words[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(words[AMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read file " + fromFileName, exception);
        }

        resultBuilder.append(SUPPLY + ",").append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY + ",").append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT + ",").append(supplyAmount - buyAmount)
                .append(System.lineSeparator());

        return resultBuilder.toString();
    }

    private void writeResultToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(data);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
