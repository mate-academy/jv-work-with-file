package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int START_VALUE = 0;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] separateDataFromFile = readFromFile(fromFileName);
        int[] reportValues = calculateReport(separateDataFromFile);
        writeInfoToFile(reportValues, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(COMMA);
                line = bufferedReader.readLine();
            }
            return builder.toString().split(COMMA);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName + e.getMessage());
        }
    }

    private int[] calculateReport(String[] dataLines) {
        int supplyValue = START_VALUE;
        int buyValue = START_VALUE;
        for (int i = 0; i < dataLines.length; i++) {
            if (dataLines[i].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(dataLines[i + 1]);
            } else if (dataLines[i].equals(BUY)) {
                buyValue += Integer.parseInt(dataLines[i + 1]);
            }
        }
        return new int[]{supplyValue, buyValue};
    }

    private void writeInfoToFile(int[] calculateReport, String toFile) {
        StringBuilder builder = new StringBuilder();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            builder.append(SUPPLY).append(COMMA).append(calculateReport[SUPPLY_INDEX])
                    .append(System.lineSeparator()).append(BUY).append(COMMA)
                    .append(calculateReport[BUY_INDEX])
                    .append(System.lineSeparator()).append(RESULT).append(COMMA)
                    .append(calculateReport[SUPPLY_INDEX] - calculateReport[BUY_INDEX]);
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write down to the file " + toFile + e.getMessage());
        }
    }
}
