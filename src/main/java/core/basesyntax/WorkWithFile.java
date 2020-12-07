package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder report = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                report.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file - " + fromFileName, e);
        }
        String[] valuesReport = report.toString().split(System.lineSeparator());
        writeFile(toFileName, getReport(valuesReport));
    }

    private static String getReport(String[] valuesReport) {
        int supply = 0;
        int buy = 0;
        for (String value: valuesReport) {
            String[] val = value.split(COMMA);
            if (val[0].equals(SUPPLY)) {
                supply += Integer.parseInt(val[1]);
            } else if (val[0].equals(BUY)) {
                buy += Integer.parseInt(val[1]);
            }
        }
        int result = supply - buy;
        StringBuilder finalReport = new StringBuilder();
        finalReport.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator());
        finalReport.append(BUY).append(COMMA).append(buy).append(System.lineSeparator());
        finalReport.append("result").append(COMMA).append(result).append(System.lineSeparator());
        return finalReport.toString();
    }

    private void writeFile(String toFileName, String finalReport) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.append(finalReport);
        } catch (IOException e) {
            throw new RuntimeException("File was not found");
        }
    }
}
