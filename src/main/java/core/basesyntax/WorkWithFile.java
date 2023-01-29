package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int ACTION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createStringResult(readFromFile(fromFileName)), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));) {
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(System.lineSeparator());
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file "
                    + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String createStringResult(String[] report) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        builder = new StringBuilder();
        for (String information : report) {
            String[] str = information.split(SPLIT);
            if (str[ACTION_INDEX].equals(BUY)) {
                buy += Integer.parseInt(str[AMOUNT_INDEX]);
            } else {
                supply += Integer.parseInt(str[AMOUNT_INDEX]);
            }
        }

        result = supply - buy;

        return builder.append(SUPPLY + SPLIT + supply + System.lineSeparator())
                .append(BUY + SPLIT + buy + System.lineSeparator())
                .append("result" + SPLIT + result + System.lineSeparator()).toString();
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file "
                    + toFileName, e);
        }
    }
}
