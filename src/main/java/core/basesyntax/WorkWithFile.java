package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        fileWrite(calculateResult(fileRead(fromFileName)), toFileName);
    }

    private String fileRead(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private String calculateResult(String dataFromFile) {
        String[] stringArray = dataFromFile.split(System.lineSeparator());
        int sumSupply = 0;
        int sumBuy = 0;
        for (String oneLine : stringArray) {
            String[] categoryAndPrice = oneLine.split(COMMA_SEPARATOR);
            if (categoryAndPrice[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(categoryAndPrice[AMOUNT_INDEX]);
            } else if (categoryAndPrice[OPERATION_TYPE_INDEX].equals(BUY)) {
                sumBuy += Integer.parseInt(categoryAndPrice[AMOUNT_INDEX]);
            }
        }
        return createResultString(sumSupply, sumBuy);
    }

    private String createResultString(int sumSupply, int sumBuy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY)
                .append(COMMA_SEPARATOR)
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA_SEPARATOR)
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA_SEPARATOR)
                .append(sumSupply - sumBuy);
        return stringBuilder.toString();
    }

    private void fileWrite(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file at path " + fileName, e);
        }
    }
}
