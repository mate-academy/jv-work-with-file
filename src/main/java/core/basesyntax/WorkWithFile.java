package core.basesyntax;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = getReport(data);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private String getReport(String data) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        StringBuilder builder = new StringBuilder();
        String[] splitArray = data.split(" ");
        for (String line : splitArray) {
            String[] lineSplit = line.split(",");
            if (lineSplit[OPERATION_TYPE_INDEX].equals("supply")) {
                supply = supply + Integer.parseInt(lineSplit[AMOUNT_INDEX]);
            } else {
                buy = buy + Integer.parseInt(lineSplit[AMOUNT_INDEX]);
            }
        }
        result = supply - buy;
        return builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
