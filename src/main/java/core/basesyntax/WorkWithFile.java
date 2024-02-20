package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String DIVIDER = ",";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final int AMOUNT = 1;
    public static final int OPERATION_TYPE = 0;
    public static final int SUPPLY_INDEX = 0;
    public static final int BOUGHT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] readingResult = readFromFile(fromFileName);
        String report = makeReport(readingResult[SUPPLY_INDEX], readingResult[BOUGHT_INDEX]);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file" + toFileName, e);
        }
    }

    private int[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int suppliesSummary = 0;
            int boughtSummary = 0;
            String[] arrayForCalc;
            String value = bufferedReader.readLine();
            while (value != null) {
                arrayForCalc = value.split(DIVIDER);
                if (arrayForCalc[OPERATION_TYPE].equals(SUPPLY)) {
                    suppliesSummary += Integer.parseInt(arrayForCalc[AMOUNT]);
                } else if (arrayForCalc[OPERATION_TYPE].equals(BUY)) {
                    boughtSummary += Integer.parseInt(arrayForCalc[AMOUNT]);
                }
                value = bufferedReader.readLine();
            }
            return new int[]{suppliesSummary, boughtSummary};
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
    }

    private String makeReport(int suppliesSummary, int boughtSummary) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY).append(DIVIDER).append(suppliesSummary)
                .append(LINE_SEPARATOR)
                .append(BUY).append(DIVIDER).append(boughtSummary)
                .append(LINE_SEPARATOR)
                .append(RESULT).append(DIVIDER).append(suppliesSummary - boughtSummary);
        return stringBuilder.toString();
    }
}
