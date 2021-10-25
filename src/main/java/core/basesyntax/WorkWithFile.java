package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = getReport(readFile(fromFileName));
        writeFile(toFileName, result);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder report = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                String[] arr = value.split(COMMA);
                for (int i = 0; i < arr.length; i++) {
                    report.append(arr[i]).append(" ");
                }
                value = reader.readLine();
            }
            return report.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
    }

    private String getReport(String data) {
        String[] splitData = data.split(" ");
        StringBuilder result = new StringBuilder();
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < splitData.length; i++) {
            if (splitData[i].equals(SUPPLY)) {
                supply += Integer.parseInt(splitData[i + 1]);
            } else if (splitData[i].equals(BUY)) {
                buy += Integer.parseInt(splitData[i + 1]);
            }
        }
        return result.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy).toString();
    }

    private void writeFile(String toFileName, String formattedResult) {
        String[] writeToFile = formattedResult.split(System.lineSeparator());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String result : writeToFile) {
                bufferedWriter.write(result + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }
}
