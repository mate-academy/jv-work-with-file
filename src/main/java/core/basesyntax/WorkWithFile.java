package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readFromFile(fromFileName);
        String report = createReport(textFromFile);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String text) {
        int supplyTotal = 0;
        int buyTotal = 0;
        final String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            final String[] words = line.split(COMA);
            int amount = Integer.parseInt(words[OPERATION_AMOUNT_INDEX]);
            if (SUPPLY.equals(words[OPERATION_NAME_INDEX])) {
                supplyTotal += amount;
            } else if (BUY.equals(words[OPERATION_NAME_INDEX])) {
                buyTotal += amount;
            }
        }
        int result = supplyTotal - buyTotal;
        return SUPPLY + COMA + supplyTotal + LINE_SEPARATOR
                + BUY + COMA + buyTotal + LINE_SEPARATOR
                + RESULT + COMA + result;
    }

    public void writeToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}

