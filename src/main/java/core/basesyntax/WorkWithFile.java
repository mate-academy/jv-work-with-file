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
        String infoFromFile = readFromFile(fromFileName);
        writeToFile(infoFromFile, toFileName);
    }

    private String readFromFile(String fromFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] readDataArray = value.split(",");
                if (readDataArray[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(readDataArray[AMOUNT_INDEX]);
                }
                if (readDataArray[OPERATION_TYPE_INDEX].equals(BUY)) {
                    buyAmount += Integer.parseInt(readDataArray[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        int resultAmount = supplyAmount - buyAmount;
        stringBuilder.append(SUPPLY)
                .append(",")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(",")
                .append(resultAmount)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String readData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(readData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
