package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        String[] input = readFromFile(fromFileName).split(System.lineSeparator());
        for (String element : input) {
            if (element.contains(BUY)) {
                buySum += Integer.parseInt(element.substring(element.indexOf(COMMA) + 1));
            }
            if (element.contains(SUPPLY)) {
                supplySum += Integer.parseInt(element.substring(element.indexOf(COMMA) + 1));
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplySum - buySum);
        writeToFile(toFileName, result);
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

    private void writeToFile(String toFileName, StringBuilder result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
