package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SERVICE_SUPPLY = "supply";
    private static final String SERVICE_BUY = "buy";
    private static final String SERVICE_RESULT = "result";
    private static final String SEPARATOR_IN_CSV_FILES = ",";
    private static final String TEMPORARY_SEPARATOR = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = getStringFromFile(fromFileName);
        String report = makeReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String[] getStringFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File fileFrom = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                stringBuilder.append(lineFromFile).append(TEMPORARY_SEPARATOR);
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileFrom, e);
        }
        return stringBuilder.toString().split(TEMPORARY_SEPARATOR);
    }

    private String makeReport(String[] dataFromFile) {
        int dailySupply = 0;
        int dailyBuy = 0;
        int dailyResult = 0;
        String[] lineFromArray = new String[2];

        for (String data : dataFromFile) {
            lineFromArray = data.split(SEPARATOR_IN_CSV_FILES);
            if (lineFromArray[0].equals(SERVICE_SUPPLY)) {
                dailySupply += Integer.parseInt(lineFromArray[1]);
            } else if (lineFromArray[0].equals(SERVICE_BUY)) {
                dailyBuy += Integer.parseInt(lineFromArray[1]);
            }
        }
        dailyResult = dailySupply - dailyBuy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SERVICE_SUPPLY).append(SEPARATOR_IN_CSV_FILES).append(dailySupply)
                .append(System.lineSeparator())
                .append(SERVICE_BUY).append(SEPARATOR_IN_CSV_FILES).append(dailyBuy)
                .append(System.lineSeparator())
                .append(SERVICE_RESULT).append(SEPARATOR_IN_CSV_FILES).append(dailyResult);
        return stringBuilder.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileTo, e);
        }
    }
}
