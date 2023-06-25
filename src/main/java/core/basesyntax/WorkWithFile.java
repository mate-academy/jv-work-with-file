package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INCREMENT_VALUE = 1;
    public static final String SUPPLY_NAME = "supply";
    public static final String BUY_NAME = "buy";
    public static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String temporaryResult = getTemporaryResult(fromFileName);
        int supplyStatistic = getSupplyStatistic(temporaryResult);
        int buyStatistic = getBuyStatistic(temporaryResult);
        writeToFile(toFileName, supplyStatistic, buyStatistic);
    }

    private String getTemporaryResult(String fromFile) {
        File fileToRead = new File(fromFile);
        StringBuilder temporaryResult = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String stringLine = bufferedReader.readLine();
            while (stringLine != null) {
                temporaryResult.append(stringLine).append(',');
                stringLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return temporaryResult.toString();
    }

    private int getSupplyStatistic(String temporaryResult) {
        String[] variablesWithValues = temporaryResult.split(",");
        int sum = 0;
        for (int i = 0; i < variablesWithValues.length; i += 2) {
            if (variablesWithValues[i].equals(SUPPLY_NAME)) {
                sum += Integer.parseInt(variablesWithValues[i + INCREMENT_VALUE]);
            }
        }
        return sum;
    }

    private int getBuyStatistic(String temporaryResult) {
        String[] variablesWithValues = temporaryResult.split(",");
        int sum = 0;
        for (int i = 0; i < variablesWithValues.length; i += 2) {
            if (variablesWithValues[i].equals(BUY_NAME)) {
                sum += Integer.parseInt(variablesWithValues[i + INCREMENT_VALUE]);
            }
        }
        return sum;
    }

    private void writeToFile(String toFile, int supplyStatistic, int buyStatistic) {
        File fileToWrite = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(SUPPLY_NAME + "," + supplyStatistic + System.lineSeparator());
            bufferedWriter.write(BUY_NAME + "," + buyStatistic + System.lineSeparator());
            bufferedWriter.write(RESULT_NAME + "," + (supplyStatistic - buyStatistic));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
