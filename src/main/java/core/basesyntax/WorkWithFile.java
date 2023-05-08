package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_REPORT = "result";
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeInFile(getResult(readFile(fromFileName).toString()), toFileName);
    }

    private StringBuilder readFile(String fromFileName) {
        StringBuilder readFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                readFile.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return readFile;
    }

    private String getResult(String fileContent) {
        String[] readFileToStringArray = fileContent.split(System.lineSeparator());
        int sumSupply = 0;
        int sumBuy = 0;
        for (String oneLine : readFileToStringArray) {
            String[] oneCategoryAndPrice = oneLine.split(",");
            if (oneCategoryAndPrice[INDEX_OF_OPERATION_TYPE].equals(SUPPLY_OPERATION)) {
                sumSupply += Integer.parseInt(oneCategoryAndPrice[INDEX_OF_AMOUNT]);
            } else if (oneCategoryAndPrice[INDEX_OF_OPERATION_TYPE].equals(BUY_OPERATION)) {
                sumBuy += Integer.parseInt(oneCategoryAndPrice[INDEX_OF_AMOUNT]);
            }
        }
        return createResultString(sumSupply, sumBuy);
    }

    private String createResultString(int sumSupply, int sumBuy) {
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY_OPERATION)
                .append(COMMA_SEPARATOR)
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY_OPERATION)
                .append(COMMA_SEPARATOR)
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT_REPORT)
                .append(COMMA_SEPARATOR)
                .append(sumSupply - sumBuy);
        return result.toString();
    }

    private void writeInFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("The file at path " + fileName + " was not written" + e);
        }
    }
}
