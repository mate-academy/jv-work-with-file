package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String DELIMITER_COMMA = ",";
    private static final int SUBSTRING_INCREASE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFromFile(fromFileName);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File fileToRead = new File(fromFileName);
        int buySum = 0;
        int supplySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                int sum = Integer.parseInt(line.substring(line.indexOf(DELIMITER_COMMA)
                        + SUBSTRING_INCREASE));
                if (line.startsWith(BUY)) {
                    buySum += sum;
                } else {
                    supplySum += sum;
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file", e);
        }
        int resultSum = supplySum - buySum;
        return new StringBuilder().append(SUPPLY).append(DELIMITER_COMMA).append(supplySum)
                .append(System.lineSeparator()).append(BUY).append(DELIMITER_COMMA)
                .append(buySum).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER_COMMA).append(resultSum).toString();
    }

    private void writeToFile(String toFileName, String report) {
        File fileToWrite = new File(toFileName);

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing into the file", e);
        }
    }
}
