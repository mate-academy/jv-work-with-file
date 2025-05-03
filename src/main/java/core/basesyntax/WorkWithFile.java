package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CAPTION_SUPPLY = "supply";
    private static final String CAPTION_BUY = "buy";
    private static final String CAPTION_RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int POSITION_OPERATION = 0;
    private static final int POSITION_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            int count = 0;
            while (line != null) {
                if (count > 0) {
                    result.append(System.lineSeparator());
                }
                result.append(line);
                count++;
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file", e);
        }
        return result.toString().split(System.lineSeparator());
    }

    private String generateReport(String[] data) {
        int supply = 0;
        int buy = 0;

        for (String line : data) {
            String[] elements = line.split(SEPARATOR);
            if (elements[POSITION_OPERATION].equals(CAPTION_SUPPLY)) {
                supply += Integer.parseInt(elements[POSITION_AMOUNT]);
            } else if (elements[POSITION_OPERATION].equals(CAPTION_BUY)) {
                buy += Integer.parseInt(elements[POSITION_AMOUNT]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(CAPTION_SUPPLY);
        result.append(SEPARATOR);
        result.append(supply);
        result.append(System.lineSeparator());
        result.append(CAPTION_BUY);
        result.append(SEPARATOR);
        result.append(buy);
        result.append(System.lineSeparator());
        result.append(CAPTION_RESULT);
        result.append(SEPARATOR);
        result.append(supply - buy);
        return result.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
