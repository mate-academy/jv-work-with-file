package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SEPARATION_SYMBOL = ",";
    private static final String SPACE_SYMBOL = " ";
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_NUMBER = 1;
    private static final String ERROR_READ_FILE = "The file was not read: ";
    private static final String ERROR_WRITE_FILE = "Data was not written to the file: ";

    public void getStatistic(String fromFileName, String toFileName) {
        String textFile = readFromFile(fromFileName);
        String report = parseDataByCategory(textFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder fileText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                fileText.append(line).append(SPACE_SYMBOL);
                line = reader.readLine();
            }
            return fileText.toString();
        } catch (IOException e) {
            throw new RuntimeException(ERROR_READ_FILE + fileName, e);
        }
    }

    private String parseDataByCategory(String data) {
        int countSupply = calculateSumByCategory(data, OPERATION_TYPE_SUPPLY);
        int countBuy = calculateSumByCategory(data, OPERATION_TYPE_BUY);
        return getReport(countSupply, countBuy);
    }

    private int calculateSumByCategory(String data, String categoryCalculate) {
        int sum = 0;
        String[] generalSplitDataArray = data.split(SPACE_SYMBOL);
        for (String generalSplitData : generalSplitDataArray) {
            String[] derivedSplitDataArray = generalSplitData.split(SEPARATION_SYMBOL);
            String category = derivedSplitDataArray[INDEX_OPERATION];
            if (category.equals(categoryCalculate)) {
                int price = Integer.parseInt(derivedSplitDataArray[INDEX_NUMBER]);
                sum += price;
            }
        }
        return sum;
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_WRITE_FILE + fileName, e);
        }
    }

    private String getReport(int countSupply, int countBuy) {
        int result = countSupply - countBuy;
        StringBuilder report = new StringBuilder();
        report.append(OPERATION_TYPE_SUPPLY)
                .append(SEPARATION_SYMBOL)
                .append(countSupply)
                .append(SEPARATOR)
                .append(OPERATION_TYPE_BUY)
                .append(SEPARATION_SYMBOL)
                .append(countBuy)
                .append(SEPARATOR)
                .append(RESULT)
                .append(SEPARATION_SYMBOL)
                .append(result);
        return report.toString();
    }
}
