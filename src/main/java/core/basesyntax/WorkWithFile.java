package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder report = new StringBuilder();
        List<String> stringFromFile = new ArrayList<>();
        readFromFile(fromFileName, stringFromFile, report);
        writeToFile(toFileName, report);
    }

    private void readFromFile(String fromFileName, List<String> stringFromFile,
                              StringBuilder report) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String read = bufferedReader.readLine();
            while (read != null) {
                stringFromFile.add(read);
                read = bufferedReader.readLine();
            }
            buildReport(stringFromFile, report);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file: " + fromFileName, e);
        }
    }

    private void buildReport(List<String> stringFromFile, StringBuilder report) {
        int buy = 0;
        int supply = 0;

        for (String record : stringFromFile) {
            String[] recordParts = record.split(COMA);
            int digit = Integer.parseInt(recordParts[1]);
            if (recordParts[0].equals(OPERATION_TYPE_BUY)) {
                buy += digit;
            } else if (recordParts[0].equals(OPERATION_TYPE_SUPPLY)) {
                supply += digit;
            }
        }

        int result = supply - buy;
        report.append(OPERATION_TYPE_SUPPLY)
                .append(COMA)
                .append(supply)
                .append(System.lineSeparator())
                .append(OPERATION_TYPE_BUY)
                .append(COMA)
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
    }

    private void writeToFile(String toFileName, StringBuilder report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write from file: " + toFileName, e);
        }
    }
}
