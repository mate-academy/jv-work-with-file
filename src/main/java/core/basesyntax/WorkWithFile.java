package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_BY = ",";
    private static final String SUPPLY_INDEX = "supply";
    private static final String BUY_INDEX = "buy";
    private static final String RESULT_INDEX = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String str = readFromFile(fromFileName);
        String str1 = createReport(str);
        writeReportToFile(toFileName,str1);
    }

    public String readFromFile(String getName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SPLIT_BY);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        String result = stringBuilder.toString();
        return result;
    }

    public String createReport(String data) {
        int supplyInt = 0;
        int buyInt = 0;
        int sum = 0;
        String[] report = data.split(SPLIT_BY);
        StringBuilder stringBuilder = new StringBuilder(SUPPLY_INDEX);
        for (int i = 0; i < report.length; i++) {
            if (report[i].equals(SUPPLY_INDEX)) {
                supplyInt += Integer.parseInt(report[i + 1]);
            }
            if (report[i].equals(BUY_INDEX)) {
                buyInt += Integer.parseInt(report[i + 1]);
            }
        }
        stringBuilder.append(SPLIT_BY).append(supplyInt).append(System.lineSeparator())
                .append(BUY_INDEX).append(SPLIT_BY)
                .append(buyInt).append(System.lineSeparator())
                .append(RESULT_INDEX).append(SPLIT_BY).append(supplyInt - buyInt);
        String result = stringBuilder.toString();
        return result;
    }

    public void writeReportToFile(String fileName, String report) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file",e);
        }
    }
}
