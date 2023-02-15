package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeReportToCsvFile(toFileName, reportFromData(readDataFromCsvFile(fromFileName)));
    }

    private String readDataFromCsvFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String reportFromData(String stringBuilder) {
        String[] dataStrings = stringBuilder.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String string : dataStrings) {
            String[] value = string.split(COMA);
            if (value[ZERO_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(value[FIRST_INDEX]);
            } else {
                buy += Integer.parseInt(value[FIRST_INDEX]);
            }
        }
        int result = supply - buy;
        return SUPPLY + COMA + supply
                + System.lineSeparator()
                + BUY + COMA + buy
                + System.lineSeparator()
                + RESULT + COMA + (result);
    }

    public void writeReportToCsvFile(String fileName, String stringBuilder) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
