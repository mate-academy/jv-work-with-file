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

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            String line = bufferedReader.readLine();
            do {
                stringBuilder.append(line).append(COMA);
                line = bufferedReader.readLine();
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException("Cant read file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String stringFromFile) {
        int supplySum = 0;
        int buySum = 0;
        String[] values = stringFromFile.split(COMA);
        for (int i = 0; i < values.length; i += 2) {
            if (values[i].equals(SUPPLY)) {
                supplySum += Integer.parseInt(values[i + 1]);
            } else {
                buySum += Integer.parseInt(values[i + 1]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY).append(COMA).append(supplySum).append(System.lineSeparator());
        result.append(BUY).append(COMA).append(buySum).append(System.lineSeparator());
        result.append(RESULT).append(COMA).append(supplySum - buySum);
        return result.toString();
    }

    public void writeToFile(String toFileName, String result) {
        try (FileWriter toFile = new FileWriter(toFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(toFile);) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write result to file " + toFileName, e);
        }
    }
}

