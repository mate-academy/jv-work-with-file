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
    private static final String COMA = ",";
    private static final int ONE_CELL = 0;
    private static final int TWO_CELL = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayValue = getReadDate(fromFileName);
        String result = createReport(arrayValue);
        writeToFile(result, toFileName);
    }

    private String[] getReadDate(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("File can't be read " + fromFileName, e);
        }

        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] values) {
        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;

        for (String value : values) {
            String[] splitValue = value.split(COMA);
            if (splitValue[ONE_CELL].contains(SUPPLY)) {
                supply += Integer.parseInt(splitValue[TWO_CELL]);
            } else if (splitValue[ONE_CELL].contains(BUY)) {
                buy += Integer.parseInt(splitValue[TWO_CELL]);
            }
        }

        return stringBuilder.append(SUPPLY).append(COMA).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(supply - buy).toString();
    }

    private void writeToFile(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("File can't be write " + toFileName, e);
        }
    }
}
