package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textFromFile = readStatisticFromFile(fromFileName);

        String report = generateReport(textFromFile);
        writeStatisticsToFile(toFileName, report);
    }

    private String[] readStatisticFromFile(String fromFileName) {
        List<String> textFromFile = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                textFromFile.add(text);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error with reading from file: " + fromFileName, e);
        }
        return textFromFile.toArray(new String[0]);
    }

    private String generateReport(String[] textFromFile) {
        int supply = 0;
        int buy = 0;

        for (String text : textFromFile) {
            String[] block = text.split(SEPARATOR);
            String operationType = block[OPERATION_INDEX].trim();
            int amount = Integer.parseInt(block[AMOUNT_INDEX].trim());

            if (operationType.equals(SUPPLY)) {
                supply += amount;
            } else if (operationType.equals(BUY)) {
                buy += amount;
            }
        }

        int result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(supply).append("\n");
        report.append(BUY).append(SEPARATOR).append(buy).append("\n");
        report.append(RESULT).append(SEPARATOR).append(result);
        return report.toString();
    }

    private void writeStatisticsToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error with writing to file: " + toFileName, e);
        }
    }
}
