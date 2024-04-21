package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFileText(fromFileName);
        String report = calculateData(data);
        writeToFile(toFileName, report);
    }

    private String[] readFileText(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("can not read file: " + fromFileName, e);
        }
    }

    private String calculateData(String[] data) {
        int supply = OPERATION;
        int buy = OPERATION;
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : data) {
            String[] value = line.split(",");
            if (value[OPERATION].equals("supply")) {
                supply += Integer.parseInt(value[AMOUNT]);
            } else {
                buy += Integer.parseInt(value[AMOUNT]);
            }
        }
        int result = supply - buy;
        return stringBuilder
                .append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
            bufferedWriter.write(toFileName);
        } catch (IOException e) {
            throw new RuntimeException("can not write to file: " + toFileName, e);
        }
    }
}
