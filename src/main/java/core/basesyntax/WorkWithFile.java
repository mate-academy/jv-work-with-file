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
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        String lines = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value;
            while ((value = reader.readLine()) != null) {
                lines += value + System.lineSeparator();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return lines;
    }

    private String generateReport(String data) {
        int valueSupply = 0;
        int valueBuy = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String value : lines) {
            String[] split = value.split(SEPARATOR);
            if (split[OPERATION_INDEX].equals(SUPPLY)) {
                valueSupply += Integer.parseInt(split[VALUE_INDEX]);
            } else if (split[OPERATION_INDEX].equals(BUY)) {
                valueBuy += Integer.parseInt(split[VALUE_INDEX]);
            }
        }
        String supply = SUPPLY + SEPARATOR + valueSupply;
        String buy = BUY + SEPARATOR + valueBuy;
        String result = RESULT + SEPARATOR + (valueSupply - valueBuy);
        return supply + System.lineSeparator() + buy + System.lineSeparator() + result;
    }

    private void writeToFile(String toFileName, String data) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
