package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int NUMBER_OF_ITEMS_IN_LINE = 2;
    private static final int INDEX_OF_AMOUNT = 0;
    private static final int INDEX_OF_OPERATION = 1;

    private File fileForRead;
    private File fileForWrite;

    public void getStatistic(String fromFileName, String toFileName) {
        fileForRead = new File(fromFileName);
        fileForWrite = new File(toFileName);
        String dataString = readFile(fileForRead);
        String stringDataToFile = generateReport(dataString);
        writeToFile(fileForWrite, stringDataToFile);
    }

    private String readFile(File file) {
        StringBuilder stringBuilderForFilesData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileForRead))) {
            int currentSymbol = bufferedReader.read();
            while (currentSymbol != -1) {
                stringBuilderForFilesData.append((char) currentSymbol);
                currentSymbol = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + file.getName(), e);
        }
        return stringBuilderForFilesData.toString();
    }

    private String generateReport(String data) {
        int variableSupply = 0;
        int variableBuy = 0;
        int variableResult;
        String[] dataStrings = data.split(System.lineSeparator());
        String[] forWriteSolution;
        for (int a = 0; a < dataStrings.length; a++) {
            forWriteSolution = dataStrings[a].split(",");
            if (forWriteSolution.length == NUMBER_OF_ITEMS_IN_LINE) {
                int transaction = Integer.valueOf(forWriteSolution[INDEX_OF_OPERATION]);
                if (forWriteSolution[INDEX_OF_AMOUNT].equals(SUPPLY)) {
                    variableSupply += transaction;
                } else if (forWriteSolution[INDEX_OF_AMOUNT].equals(BUY)) {
                    variableBuy += transaction;
                }
            }
        }
        variableResult = variableSupply - variableBuy;
        return SUPPLY + "," + variableSupply + System.lineSeparator() + BUY
                + "," + variableBuy + System.lineSeparator() + RESULT + "," + variableResult;
    }

    private void writeToFile(File file, String dataForWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForWrite))) {
            bufferedWriter.write(dataForWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + file.getName(), e);
        }
    }
}
