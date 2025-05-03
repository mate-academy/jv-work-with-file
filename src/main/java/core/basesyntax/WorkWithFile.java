package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_TYPE = "supply,";
    private static final String BUY_TYPE = "buy,";
    private static final String RESULT_TYPE = "result,";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SEPARATOR = ",";
    private static final int MINUS_ONE_INDEX = -1;

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFile(fromFileName);
        String report = generateReport(readData);
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFileName) {
        StringBuilder readData = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != MINUS_ONE_INDEX) {
                char c = (char) value;
                readData.append(c);
                value = bufferedReader.read();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return readData.toString();
    }

    public void writeToFile(String toFileName, String data) {
        try (FileWriter myWriter = new FileWriter(toFileName)) {
            myWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String generateReport(String data) {
        int supplySum = getCalculations(data, SUPPLY_TYPE);
        int buySum = getCalculations(data, BUY_TYPE);
        int result = supplySum - buySum;

        return String.format("%s%d%n%s%d%n%s%d", SUPPLY_TYPE, supplySum,
                BUY_TYPE, buySum,
                RESULT_TYPE, result);
    }

    public int getCalculations(String data, String type) {
        int calculatedSum = 0;
        String[] parts;
        for (String line : data.split(NEW_LINE)) {
            if (line.startsWith(type)) {
                parts = line.split(SEPARATOR);
                for (int i = 1; i < parts.length; i += 2) {
                    calculatedSum += Integer.parseInt(parts[i].trim());
                }
            }
        }
        return calculatedSum;
    }
}
