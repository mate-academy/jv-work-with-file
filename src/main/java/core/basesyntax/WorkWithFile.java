package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLITTER_FOR_FILE_NAME = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int AMOUNT_SUPPLY = 0;
    private static final int AMOUNT_BUY = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] split = stringBuilder.toString().split(System.lineSeparator());
        int buyValue = AMOUNT_BUY;
        int supplyValue = AMOUNT_SUPPLY;
        for (String s : split) {
            String[] operationAndAmount = s.split(SPLITTER_FOR_FILE_NAME);
            if (operationAndAmount[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            } else {
                buyValue += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            }
        }
        int result = supplyValue - buyValue;
        StringBuilder stringBuilderReport = new StringBuilder(SUPPLY)
                .append(",").append(supplyValue).append(System.lineSeparator())
                        .append(BUY).append(",").append(buyValue).append(System.lineSeparator())
                        .append(RESULT).append(",").append(result);
        String fileReport = stringBuilderReport.toString();
        File fileWriter = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWriter,true))) {
            bufferedWriter.write(fileReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
