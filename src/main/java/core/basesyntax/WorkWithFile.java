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
    public static final String C_R_L_F = System.lineSeparator();
    public static final int POS_OF_AMOUNT = 1;
    public static final int POS_OF_NAME = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String input = getFromFile(fromFileName);
        writeToFile(input, toFileName);
    }

    public void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }

    private String getFromFile(String fromFileName) {
        int suppliesSummary = 0;
        int buySummary = 0;
        String output;
        String[] arrayForCalc = new String[2];
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                arrayForCalc = value.split(DIVIDER);
                if (arrayForCalc[POS_OF_NAME].equals(SUPPLY)) {
                    suppliesSummary += Integer.parseInt(arrayForCalc[POS_OF_AMOUNT]);
                } else if (arrayForCalc[POS_OF_NAME].equals(BUY)) {
                    buySummary += Integer.parseInt(arrayForCalc[POS_OF_AMOUNT]);
                }
                value = bufferedReader.readLine();
            }
            output = stringBuilder
                    .append(SUPPLY).append(DIVIDER).append(suppliesSummary).append(C_R_L_F)
                    .append(BUY).append(DIVIDER).append(buySummary).append(C_R_L_F)
                    .append(RESULT).append(DIVIDER).append(suppliesSummary - buySummary).toString();
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
        return output;
    }
}
