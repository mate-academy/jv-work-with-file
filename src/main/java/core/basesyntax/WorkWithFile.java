package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String VALUE_DELIMITER = ",";
    private static final String SUPPLY_DATA = "supply";
    private static final String BUY_DATA = "buy";
    private static final String RESULT_DATA = "result";
    private static final int COMPARISON_INDEX = 0;
    private static final int TAKING_DATA_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = null;
        String[] splitWords = readFile(fromFileName).toString().split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String temp : splitWords) {
            String[] splitValues = temp.split(VALUE_DELIMITER);
            if (splitValues[COMPARISON_INDEX].equals(SUPPLY_DATA)) {
                supply += Integer.parseInt(splitValues[TAKING_DATA_INDEX]);
            } else if (splitValues[COMPARISON_INDEX].equals(BUY_DATA)) {
                buy += Integer.parseInt(splitValues[TAKING_DATA_INDEX]);
            }
        }
        result = supply - buy;
        this.writeFile(result, supply, buy, toFileName);
    }

    private StringBuilder readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String readLine = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                System.out.println("File is empty");
            }
            while (readLine != null) {
                stringBuilder.append(readLine).append(System.lineSeparator());
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    private void writeFile(int result, int supply, int buy, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getStringStatistic(result,supply,buy));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringStatistic(int result, int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(SUPPLY_DATA)
                .append(VALUE_DELIMITER)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY_DATA)
                .append(VALUE_DELIMITER)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT_DATA)
                .append(VALUE_DELIMITER)
                .append(result);
        return new String(stringBuilder);
    }
}
