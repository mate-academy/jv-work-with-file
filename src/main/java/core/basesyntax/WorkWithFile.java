package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName);
        calculateResult(readDataFromFile(fromFileName));
        writeDataToFile(toFileName, calculateResult(readDataFromFile(fromFileName)));
    }

    private String[] readDataFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                builder.append(currentLine).append(System.lineSeparator());
                currentLine = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String calculateResult(String[] splitStrings) {
        int supplySum = 0;
        int buySum = 0;
        for (String currentString : splitStrings) {
            String[] twoValues = currentString.split(COMMA);
            if (twoValues[FIRST_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(twoValues[SECOND_INDEX]);
            }
            if (twoValues[FIRST_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(twoValues[SECOND_INDEX]);
            }
        }
        int result = supplySum - buySum;

        return new StringBuilder().append(SUPPLY).append(COMMA)
                .append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append("result").append(COMMA).append(result).toString();
    }

    private void writeDataToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(result);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file:" + toFileName, e);
        }
    }
}
