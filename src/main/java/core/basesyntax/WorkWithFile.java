package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }
    
    private String readFromFile(String fromFileName) {
        int supplyValue = 0;
        int buyValue = 0;

        File fromFile = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitted = line.split(SEPARATOR);
                if (splitted[KEY_INDEX].equals(BUY)) {
                    buyValue += Integer.parseInt(splitted[VALUE_INDEX]);
                } else {
                    supplyValue += Integer.parseInt(splitted[VALUE_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find input file", e);
        }

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(supplyValue).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buyValue).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supplyValue - buyValue);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write output file", e);
        }
    }
}
