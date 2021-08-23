package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INDEX_OF_AMOUNT = 1;
    public static final int INDEX_OF_OPERATION_TYPE = 0;
    public static final String ELEMENT_TO_SEPARATE = " ";
    public static final String SYMBOL_COMA = ",";
    public static final String OPERATOR_TYPE_SUPPLY = "supply";
    public static final String OPERATOR_TYPE_BUY = "buy";
    public static final String OPERATOR_TYPE_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] dataArray = readFromFile(fromFileName);
        String report = createReport(dataArray);
        writeToFile(toFileName, report);
    }

    public String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(ELEMENT_TO_SEPARATE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return stringBuilder.toString().split(ELEMENT_TO_SEPARATE);
    }

    public String createReport(String [] dataArray) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (String data : dataArray) {
            String[] lineArray = data.split(SYMBOL_COMA);
            int sum = Integer.parseInt(lineArray[INDEX_OF_AMOUNT]);
            if (lineArray[INDEX_OF_OPERATION_TYPE].equals(OPERATOR_TYPE_SUPPLY)) {
                sumOfSupply += sum;
            } else {
                sumOfBuy += sum;
            }
        }
        return OPERATOR_TYPE_SUPPLY
                + SYMBOL_COMA
                + sumOfSupply
                + System.lineSeparator()
                + OPERATOR_TYPE_BUY
                + SYMBOL_COMA
                + sumOfBuy
                + System.lineSeparator()
                + OPERATOR_TYPE_RESULT
                + SYMBOL_COMA
                + (sumOfSupply - sumOfBuy);
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
