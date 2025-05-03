package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            String line = bufferedReader.readLine();
            do {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException("Cant read file " + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] stringFromFile) {
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0; i < stringFromFile.length; i++) {
            String[] valuesRow = stringFromFile[i].split(COMA);
            if (valuesRow[TYPE_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(valuesRow[VALUE_INDEX]);
            } else {
                buySum += Integer.parseInt(valuesRow[VALUE_INDEX]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY).append(COMA).append(supplySum).append(System.lineSeparator());
        result.append(BUY).append(COMA).append(buySum).append(System.lineSeparator());
        result.append(RESULT).append(COMA).append(supplySum - buySum);
        return result.toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (FileWriter toFile = new FileWriter(toFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(toFile);) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write result to file " + toFileName, e);
        }
    }
}
