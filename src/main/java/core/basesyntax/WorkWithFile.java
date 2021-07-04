package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String COMMA = ",";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fileRead = new File(fromFileName);
        File fileWrite = new File(toFileName);
        String[] dataArray = writeToStringBuilder(fileRead)
                .toString().split(System.lineSeparator());
        int supplyTotal = 0;
        int buyTotal = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (getOperationType(dataArray[i]).equals(SUPPLY)) {
                supplyTotal += getAmount(dataArray[i]);
            } else {
                buyTotal += getAmount(dataArray[i]);
            }
        }
        String reportData = generateReportData(supplyTotal, buyTotal);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite))) {
            bufferedWriter.write(reportData);
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file", exception);
        }

    }

    private String generateReportData(int supplyTotal, int buyTotal) {
        StringBuilder reportData = new StringBuilder();
        reportData.append(SUPPLY)
                .append(COMMA)
                .append(supplyTotal)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(buyTotal)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(supplyTotal - buyTotal);
        return reportData.toString();
    }

    private StringBuilder writeToStringBuilder(File fileRead) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileRead))) {
            String value = reader.readLine();
            while (value != null) {
                data.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read from file", exception);
        }
        return data;
    }

    private String getOperationType(String oneRecord) {
        return oneRecord.split(COMMA)[0];
    }

    private int getAmount(String oneRecord) {
        return Integer.parseInt(oneRecord.split(COMMA)[1]);
    }
}
