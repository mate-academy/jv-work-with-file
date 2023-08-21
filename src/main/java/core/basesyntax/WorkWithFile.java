package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_CONST = "supply";
    private static final String BUY_CONST = "buy";
    private static final String SPLIT_OPERATOR = ",";
    private static final String LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = countReport(data);
        writeReport(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                dataFromFile.append(value).append(LINE);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return dataFromFile.toString();
    }

    private String countReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] strings = dataFromFile.split(LINE);
        for (String string : strings) {
            String[] count = string.split(SPLIT_OPERATOR);
            if (count[0].equals(SUPPLY_CONST)) {
                supply += Integer.parseInt(count[1]);
            } else {
                buy += Integer.parseInt(count[1]);
            }
        }
        int result = supply - buy;
        return SUPPLY_CONST + SPLIT_OPERATOR + supply + LINE
                + BUY_CONST + SPLIT_OPERATOR + buy + LINE
                + "result" + SPLIT_OPERATOR + result + LINE;
    }

    private void writeReport(String resultData, String toFileName) {
        try (BufferedWriter report = new BufferedWriter(new FileWriter(toFileName))) {
            report.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: ", e);
        }
    }
}
