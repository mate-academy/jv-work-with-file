package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String DATA_SEPARATOR = ",";
    public static final int ENTRY_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        for (String line: readFromFile(fromFileName).split(System.lineSeparator())) {
            String[] entryType = line.split(DATA_SEPARATOR);
            int amount = Integer.parseInt(entryType[AMOUNT_INDEX]);
            if ("supply".equals(entryType[ENTRY_TYPE_INDEX])) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        writeToFile(generateString(supply, buy), toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Check your file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String generateString(int supply, int buy) {
        return new StringBuilder().append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).toString();
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write " + toFileName, e);
        }
    }
}
