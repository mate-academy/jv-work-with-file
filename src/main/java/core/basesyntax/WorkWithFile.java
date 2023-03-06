package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Integer.parseInt;


public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String string = readFromFile(fromFileName);
        String result = resultOfDataReading(string);
        recordToFile(result,toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(COMA);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("This file cannot be read ", e);
        }
        return stringBuilder.toString();
    }

    private String resultOfDataReading(String dataOfFile) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] dataArray = dataOfFile.split(",");
        int amountOfSupplies = 0;
        int amountOfBuy = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals(SUPPLY)) {
                String value = dataArray[i + 1];
                amountOfSupplies = amountOfSupplies + parseInt(value);
                i++;
            }
            if (dataArray[i].equals(BUY)) {
                String value = dataArray[i + 1];
                amountOfBuy = amountOfBuy + parseInt(value);
                i++;
            }
        }
        int result = amountOfSupplies - amountOfBuy;
        stringBuilder.append(SUPPLY).append(COMA).append(amountOfSupplies)
                .append(System.lineSeparator()).append(BUY).append(COMA)
                .append(amountOfBuy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(result);

        return stringBuilder.toString();
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
