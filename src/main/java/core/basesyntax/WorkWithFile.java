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
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = getDataFromFile(fromFileName);
        String report = makeReport(data);
        writeToFile(toFileName, report);
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
            throw new RuntimeException("Can't read the data from the file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String makeReport(String data) {
        String[] stringsData = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String stringData : stringsData) {
            String[] items = stringData.split(",");
            String itemType = items[TYPE_INDEX];
            int itemValue = Integer.parseInt(items[VALUE_INDEX]);
            if (SUPPLY.equals(itemType)) {
                supply += itemValue;
            } else if (BUY.equals(itemType)) {
                buy += itemValue;
            }
        }
        int result = supply - buy;
        return new StringBuilder().append(SUPPLY).append(COMMA).append(supply).append(SEPARATOR)
                .append(BUY).append(COMMA).append(buy).append(SEPARATOR)
                .append(RESULT).append(COMMA).append(result).toString();
    }

    private void writeToFile(String toFileName, String report) {
        File fileReport = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileReport))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into the file" + toFileName, e);
        }
    }
}
