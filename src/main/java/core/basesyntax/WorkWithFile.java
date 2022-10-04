package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RESULT = "result";
    private static final String SUPPLY_OPERATOR = "supply";
    private static final String BUY_OPERATOR = "buy";
    private static final String COMMA = ",";
    private static final int INDEX_OF_OPERATOR = 0;
    private static final int INDEX_OF_NUMBER = 1;
    private static final String CANT_WRITE = "Can't write to file. ";
    private static final String CANT_READ = "Can't read from file. ";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        StringBuilder builder = getReport(dataFromFile);
        writeToFile(toFileName, builder);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(CANT_READ + e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void writeToFile(String fileName, StringBuilder stringBuilder) {
        String result = stringBuilder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException(CANT_WRITE + e);
        }
    }

    private StringBuilder getReport(String[] dataFromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        int sumSupply = 0;
        int sumBuy = 0;
        int result;
        for (String lineInArr : dataFromFile) {
            String[] arrWithOperatorAndNumber = lineInArr.split(COMMA);
            switch (arrWithOperatorAndNumber[INDEX_OF_OPERATOR]) {
                case SUPPLY_OPERATOR:
                    sumSupply += Integer.parseInt(arrWithOperatorAndNumber[INDEX_OF_NUMBER]);
                    break;
                case BUY_OPERATOR:
                    sumBuy += Integer.parseInt(arrWithOperatorAndNumber[INDEX_OF_NUMBER]);
                    break;
                default:
                    break;
            }
        }
        result = sumSupply - sumBuy;
        stringBuilder.append(SUPPLY_OPERATOR).append(COMMA)
                .append(sumSupply).append(System.lineSeparator())
                .append(BUY_OPERATOR).append(COMMA)
                .append(sumBuy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return stringBuilder;
    }
}
