package core.basesyntax;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class WorkWithFile {
    private static final String SPLITERATOR = ",";
    private static final String CASE_BUY = "buy";
    private static final String CASE_SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String EXCEPTION_MESSAGE = "Key doesn't found";
    private static final int KEY = 0;
    private static final int KEY_VALUE = 1;
    private static final String SPLIT_BY_NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String toFileName, String report) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(report);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private String generateReport(String data) {
        StringBuilder report = new StringBuilder();
        int sumForBuy = 0;
        int sumForSupply = 0;
        for (String s : data.split(SPLIT_BY_NEW_LINE)) {
            String[] currentLine = s.split(SPLITERATOR);
            switch (currentLine[KEY]) {
                case CASE_BUY -> sumForBuy += Integer.parseInt(currentLine[KEY_VALUE]);
                case CASE_SUPPLY -> sumForSupply += Integer.parseInt(currentLine[KEY_VALUE]);
                default -> throw new NoSuchElementException(EXCEPTION_MESSAGE);
            }
        }
        return report.append(CASE_SUPPLY)
                .append(SPLITERATOR)
                .append(sumForSupply)
                .append(System.lineSeparator())
                .append(CASE_BUY)
                .append(SPLITERATOR)
                .append(sumForBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(SPLITERATOR)
                .append(sumForSupply - sumForBuy)
                .toString();
    }

    private String readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (FileReader reader = new FileReader(fromFileName)) {
            int value;
            while ((value = reader.read()) != -1) {
                data.append((char)value);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return data.toString();
    }
}
