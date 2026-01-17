package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT_LABEL = "result";
    private static final String COMMA = ",";
    private static final int TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String report = createReport(data);
        writeFile(toFileName, report);
    }

    private String[] readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return lines.toArray(new String[0]);
    }

    private String createReport(String[] data) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : data) {
            String[] entry = line.split(COMMA);
            int amount = Integer.parseInt(entry[AMOUNT_INDEX]);
            if (entry[TYPE_INDEX].equals(OPERATION_SUPPLY)) {
                totalSupply += amount;
            } else if (entry[TYPE_INDEX].equals(OPERATION_BUY)) {
                totalBuy += amount;
            }
        }

        int result = totalSupply - totalBuy;

        return OPERATION_SUPPLY + COMMA + totalSupply
                + System.lineSeparator() + OPERATION_BUY + COMMA + totalBuy
                + System.lineSeparator() + RESULT_LABEL + COMMA + result;
    }

    private void writeFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
