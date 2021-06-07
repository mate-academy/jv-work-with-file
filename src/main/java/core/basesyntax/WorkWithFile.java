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
    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_SPACE = " ";
    private static final String DELIMITER_EMPTY = "";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFromFile(fromFileName);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File fileToRead = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(" ").append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file", e);
        }
        String[] resultingString = stringBuilder.toString().trim().split(DELIMITER_SPACE);
        int buySum = 0;
        int supplySum = 0;
        String[] stringSplit;
        for (String line : resultingString) {
            stringSplit = line.split(DELIMITER_COMMA);
            if (stringSplit[OPERATION_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(stringSplit[AMOUNT_INDEX]);
            } else {
                supplySum += Integer.parseInt(stringSplit[AMOUNT_INDEX]);
            }
        }
        int resultSum = supplySum - buySum;
        return new StringBuilder().append("supply,").append(supplySum)
                .append(System.lineSeparator()).append("buy,")
                .append(buySum).append(System.lineSeparator())
                .append("result,").append(resultSum).toString();
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
