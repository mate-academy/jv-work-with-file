package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final int ACTION_INDEX = 0;
    public static final int AMMOUNT_INDEX = 1;
    public static final String BUY_INDEX = "buy";
    public static final String SUPPLY_INDEX = "supply";
    public static final String SEPARATOR_INDEX = ",";
    public static final String RESULT_INDEX = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String doneData = readFile(fromFileName);
        String readyReport = convertFile(doneData);
        writeReport(readyReport, toFileName);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private String convertFile(String recievedData) {
        String[] report = recievedData.split(System.lineSeparator());
        StringBuilder convertedText = new StringBuilder();
        int buy = 0;
        int supply = 0;
        for (String line: report) {
            String[] etteration = line.split(SEPARATOR_INDEX);
            if (etteration[ACTION_INDEX].equals(SUPPLY_INDEX)) {
                supply += Integer.parseInt(etteration[AMMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(etteration[AMMOUNT_INDEX]);
            }
        }
        convertedText.append(SUPPLY_INDEX).append(SEPARATOR_INDEX).append(supply)
                .append(System.lineSeparator())
                .append(BUY_INDEX).append(SEPARATOR_INDEX).append(buy)
                .append(System.lineSeparator())
                .append(RESULT_INDEX).append(SEPARATOR_INDEX).append(supply - buy);
        return convertedText.toString();
    }

    private void writeReport(String converted, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(converted);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to new file", e);
        }
    }
}
