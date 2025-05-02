package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int ADDITIONAL_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String getAllData = readFromFile(fromFileName);
        String report = generateReport(getAllData);
        writeResultToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(COMMA);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String generateReport(String allData) {
        int buy = 0;
        int supply = 0;
        String[] arrayWithData = allData.split(COMMA);
        for (int i = 0; i < arrayWithData.length; i++) {
            if (arrayWithData[i].equals(BUY)) {
                buy += Integer.parseInt(arrayWithData[i + ADDITIONAL_NUMBER]);
            } else if (arrayWithData[i].equals(SUPPLY)) {
                supply += Integer.parseInt(arrayWithData[i + ADDITIONAL_NUMBER]);
            }
        }
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return reportBuilder.toString();
    }

    private void writeResultToFile(String report, String toFileName) {
        File writeInfoTo = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeInfoTo))) {
            writer.write(report);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + writeInfoTo, e);
        }
    }
}
