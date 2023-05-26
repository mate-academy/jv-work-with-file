package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String SEPARATE_FOR_LINE = "%&%";
    private static final String SEPARATE_CURRENT_LINE = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeStatisticToFile(toFileName, generateReport(readStatisticFromFile(fromFileName)));
    }

    private String readStatisticFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(SEPARATE_FOR_LINE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't not read file:" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private int[] generateReport(String incomeLine) {
        int supplyValue = 0;
        int buyValue = 0;
        String[] split = incomeLine.split(SEPARATE_FOR_LINE);
        for (String cureent : split) {
            String[] splitCurrentLine = cureent.split(SEPARATE_CURRENT_LINE);
            if (splitCurrentLine[OPERATION_INDEX].equals(OPERATION_SUPPLY)) {
                supplyValue += Integer.parseInt(splitCurrentLine[VALUE_INDEX]);
            }
            if (splitCurrentLine[OPERATION_INDEX].equals(OPERATION_BUY)) {
                buyValue += Integer.parseInt(splitCurrentLine[VALUE_INDEX]);
            }
        }
        return new int[]{supplyValue, buyValue};
    }

    private void writeStatisticToFile(String toFileName, int[] reportDate) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(OPERATION_SUPPLY + SEPARATE_CURRENT_LINE
                    + reportDate[OPERATION_INDEX] + System.lineSeparator());
            bufferedWriter.write(OPERATION_BUY + SEPARATE_CURRENT_LINE
                    + reportDate[VALUE_INDEX] + System.lineSeparator());
            bufferedWriter.write(OPERATION_RESULT + SEPARATE_CURRENT_LINE
                    + (reportDate[OPERATION_INDEX] - reportDate[VALUE_INDEX]));
        } catch (IOException e) {
            throw new RuntimeException("Can't not write data to file:" + toFileName, e);
        }
    }
}
