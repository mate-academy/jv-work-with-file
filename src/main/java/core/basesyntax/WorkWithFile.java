package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMOUNT_INDEX = 1;
    private static final String COLUMN_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        int currentAmount;
        String[] splittedLine;

        String[] linesFromFile = readFromFile(fromFileName);
        for (String line: linesFromFile) {
            splittedLine = line.split(COLUMN_SEPARATOR);
            currentAmount = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            if (splittedLine[OPERATION_TYPE_INDEX].equals("supply")) {
                supplyTotal += currentAmount;
            } else {
                buyTotal += currentAmount;
            }
        }
        writeToFile(toFileName, supplyTotal, buyTotal);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file" + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeToFile(String toFileName, int supplyTotal, int buyTotal) {
        File outputFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write("supply," + supplyTotal + System.lineSeparator());
            bufferedWriter.write("buy," + buyTotal + System.lineSeparator());
            bufferedWriter.write("result," + (supplyTotal - buyTotal) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't create output file "
                    + toFileName + " or write to file", e);
        }
    }
}
