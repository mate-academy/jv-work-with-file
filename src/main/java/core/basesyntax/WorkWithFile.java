package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "\\W+";
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String fromFileData = readFromFile(fromFileName);
        String report = genarateReport(fromFileData);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String genarateReport(String fromFileData) {
        StringBuilder actualResult = new StringBuilder();
        String[] files = fromFileData.split(DELIMITER);
        int buy = 0;
        int supply = 0;
        int result;
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(BUY)) {
                buy = buy + Integer.parseInt(files[i + 1]);
            }
            if (files[i].equals(SUPPLY)) {
                supply = supply + Integer.parseInt(files[i + 1]);
            }
        }
        result = supply - buy;
        actualResult.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                    .append(RESULT).append(COMMA).append(result).append(System.lineSeparator());
        return actualResult.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't writer data from the file " + toFileName, e);
        }
    }
}

