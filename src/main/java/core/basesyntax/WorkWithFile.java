package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final String SEPARATOR_COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readFromFile(fromFileName);
        String report = createReport(textFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(final String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file.", e);
        }
    }

    private String createReport(final String data) {
        int supply = 0;
        int buy = 0;
        final String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            final String[] words = line.split(SEPARATOR_COMMA);
            int amount = Integer.parseInt(words[OPERATION_AMOUNT_INDEX]);
            if (OPERATION_SUPPLY.equals(words[OPERATION_NAME_INDEX])) {
                supply += amount;
            } else if (OPERATION_BUY.equals(words[OPERATION_NAME_INDEX])) {
                buy += amount;
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(final String toFileName, final String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file.", e);
        }
    }
}
