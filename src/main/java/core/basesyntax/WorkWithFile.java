package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String READ_ERROR_MSG = "Can't read from this file: ";
    private static final String WRITE_ERROR_MSG = "Can't write to this file: ";
    private static final String SEPARATOR_REGEX = ",";
    private static final String SUPPLY_LINE = "supply";
    private static final String BUY_LINE = "buy";
    private static final String RESULT_LINE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                dataFromFile.append(value).append(SEPARATOR_REGEX);
                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MSG + fromFileName, e);
        }

        return dataFromFile.toString();
    }

    private String createReport(String dataFromFile) {
        String[] parts = dataFromFile.split(SEPARATOR_REGEX);
        int supplyTotal = 0;
        int buyTotal = 0;

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(SUPPLY_LINE)) {
                supplyTotal += Integer.parseInt(parts[++i]);
            } else if (parts[i].equals(BUY_LINE)) {
                buyTotal += Integer.parseInt(parts[++i]);
            }
        }

        return SUPPLY_LINE + SEPARATOR_REGEX + supplyTotal + System.lineSeparator()
                + BUY_LINE + SEPARATOR_REGEX + buyTotal + System.lineSeparator()
                + RESULT_LINE + SEPARATOR_REGEX + (supplyTotal - buyTotal) + System.lineSeparator();
    }

    private void writeToFile(String text, String toFileName) {
        File file = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(WRITE_ERROR_MSG + toFileName, e);
        }
    }
}
