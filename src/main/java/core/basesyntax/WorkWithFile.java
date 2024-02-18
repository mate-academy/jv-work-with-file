package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = makeReport(fromFileName);
        File fileReport = new File(toFileName);
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileReport))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file ", e);
        }
    }

    private String getDataFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return builder.toString();
    }

    private String makeReport(String fromFileName) {
        String data = getDataFromFile(fromFileName);
        StringBuilder report = new StringBuilder();
        String[] stringsData = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String stringData : stringsData) {
            String[] items = stringData.split(",");
            String itemType = items[TYPE_INDEX];
            int itemValue = Integer.parseInt(items[VALUE_INDEX]);
            if ("supply".equals(itemType)) {
                supply += itemValue;
            } else if ("buy".equals(itemType)) {
                buy += itemValue;
            }
        }
        int result = supply - buy;
        String stringReport = report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).toString();
        return stringReport;
    }
}
