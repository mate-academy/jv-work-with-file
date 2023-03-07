package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DEFAULT_SUPPLY = "supply";
    private static final String DEFAULT_BUY = "buy";
    private static final String DEFAULT_RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String string = readFromFile(fromFileName);
        String result = resultOfDataReading(string);
        recordToFile(result, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataToString = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataToString.append(line).append(COMA);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        }
        return dataToString .toString();
    }

    private String resultOfDataReading(String dataOfFile) {
        StringBuilder resultString = new StringBuilder();
        String[] dataArray = dataOfFile.split(COMA);
        int amountOfSupplies = 0;
        int amountOfBuy = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals(DEFAULT_SUPPLY)) {
                String value = dataArray[i + 1];
                amountOfSupplies = amountOfSupplies + Integer.parseInt(value);
                i++;
            }
            if (dataArray[i].equals(DEFAULT_BUY)) {
                String value = dataArray[i + 1];
                amountOfBuy = amountOfBuy + Integer.parseInt(value);
                i++;
            }
        }
        int result = amountOfSupplies - amountOfBuy;
        resultString.append(DEFAULT_SUPPLY).append(COMA).append(amountOfSupplies)
                .append(System.lineSeparator()).append(DEFAULT_BUY).append(COMA)
                .append(amountOfBuy).append(System.lineSeparator())
                .append(DEFAULT_RESULT).append(COMA).append(result);

        return resultString.toString();
    }

    private void recordToFile(String stringForWritingToFile, String toFileName) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(stringForWritingToFile);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
