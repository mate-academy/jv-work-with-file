package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String WHITE_SPACE = " ";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int PRICE_INDEX = 1;
    private static final int OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String information = readFromFile(fromFileName);
        String report = createReport(information);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder info = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                info.append(value).append(WHITE_SPACE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return info.toString();
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] array = data.split(WHITE_SPACE);
        for (String operation : array) {
            if (operation.split(COMMA)[OPERATION_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(operation.split(COMMA)[PRICE_INDEX]);
            } else if (operation.split(COMMA)[OPERATION_INDEX].equals(BUY)) {
                buy += Integer.parseInt(operation.split(COMMA)[PRICE_INDEX]);
            }
        }
        result = supply - buy;
        return (SUPPLY + COMMA + supply + LINE_SEPARATOR
                + BUY + COMMA + buy + LINE_SEPARATOR
                + RESULT + COMMA + result);
    }
}
