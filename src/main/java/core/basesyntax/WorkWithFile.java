package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        String[] data = readFromFile(fromFileName).split(System.lineSeparator());
        StringBuilder calculateResult = new StringBuilder();
        for (String unit : data) {
            if (unit.substring(0, unit.indexOf(COMMA)).equals(SUPPLY)) {
                supplySum += Integer.parseInt(unit.substring(unit.indexOf(COMMA) + 1));
            }
            if (unit.substring(0, unit.indexOf(COMMA)).equals(BUY)) {
                buySum += Integer.parseInt(unit.substring(unit.indexOf(COMMA) + 1));
            }
        }
        calculateResult.append(SUPPLY).append(COMMA).append(supplySum)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA)
                .append(supplySum - buySum).append(System.lineSeparator());
        writeToFile(toFileName, calculateResult);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder textFromFileBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                textFromFileBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        return textFromFileBuilder.toString();
    }

    private void writeToFile(String toFileName, StringBuilder calculateResult) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculateResult.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}

