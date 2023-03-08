package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String string = readFromFile(fromFileName);
        String result = generateReport(string);
        writeToFile(result, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataToString = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataToString.append(line).append(SEPARATOR);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        }
        return dataToString.toString();
    }

    private String generateReport(String fileData) {
        StringBuilder resultString = new StringBuilder();
        String[] dataArray = fileData.split(SEPARATOR);
        int amountOfSupplies = 0;
        int amountOfBuy = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals(OPERATION_SUPPLY)) {
                String value = dataArray[i + 1];
                amountOfSupplies = amountOfSupplies + Integer.parseInt(value);
                i++;
            }
            if (dataArray[i].equals(OPERATION_BUY)) {
                String value = dataArray[i + 1];
                amountOfBuy = amountOfBuy + Integer.parseInt(value);
                i++;
            }
        }
        int result = amountOfSupplies - amountOfBuy;
        resultString.append(OPERATION_SUPPLY).append(SEPARATOR)
                .append(amountOfSupplies).append(System.lineSeparator())
                .append(OPERATION_BUY).append(SEPARATOR )
                .append(amountOfBuy).append(System.lineSeparator())
                .append(OPERATION_RESULT).append(SEPARATOR).append(result);

        return resultString.toString();
    }

    private void writeToFile(String content, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
