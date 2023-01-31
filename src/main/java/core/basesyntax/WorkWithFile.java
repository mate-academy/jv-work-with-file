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
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createStringResult(readFromFile(fromFileName)), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));) {
            String data = reader.readLine();
            while (data != null) {
                stringBuilder.append(data).append(System.lineSeparator());
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file "
                    + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createStringResult(String[] report) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        stringBuilder = new StringBuilder();
        for (String information : report) {
            String[] linesFromFile = information.split(SPLIT);
            if (linesFromFile[ACTION_INDEX].equals(BUY)) {
                buy += Integer.parseInt(linesFromFile[AMOUNT_INDEX]);
            } else {
                supply += Integer.parseInt(linesFromFile[AMOUNT_INDEX]);
            }
        }

        result = supply - buy;

        return stringBuilder.append(SUPPLY + SPLIT + supply + System.lineSeparator())
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
