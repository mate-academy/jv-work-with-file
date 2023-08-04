package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String VALUE_BUY = "buy";
    private static final String VALUE_SUPPLY = "supply";
    private static final String VALUE_RESULT = "result";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileRead = readingFromFile(fromFileName);
        String completedReport = generateReport(fileRead);
        writeReportToFile(toFileName, completedReport);
    }

    private String readingFromFile(String fileName) {
        File fromFile = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String valueString = bufferedReader.readLine();
            while ((valueString) != null) {
                stringBuilder.append(valueString).append(System.lineSeparator());
                valueString = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
    }

    private String generateReport(String informationForReport) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] splitFileRead = informationForReport.split("\\W+");
        for (int i = 0; i < splitFileRead.length; i++) {

            if (splitFileRead[i].equals(VALUE_SUPPLY)) {
                sumSupply += Integer.parseInt(splitFileRead[i + 1]);
            }
            if (splitFileRead[i].equals(VALUE_BUY)) {
                sumBuy += Integer.parseInt(splitFileRead[i + 1]);
            }
        }
        int result = sumSupply - sumBuy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(VALUE_SUPPLY).append(COMMA_SEPARATOR)
                .append(sumSupply + System.lineSeparator())
                .append(VALUE_BUY).append(COMMA_SEPARATOR)
                .append(sumBuy).append(System.lineSeparator())
                .append(VALUE_RESULT).append(COMMA_SEPARATOR)
                .append(result);
        return stringBuilder.toString();
    }

    private void writeReportToFile(String toFileName, String completedReport) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(completedReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
