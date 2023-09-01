package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_SUPPLY = 0;
    private static final int INDEX_BUY = 1;
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_QUANTITY = 1;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFile(fromFileName);
        String data = createReport(lines);
        writeFile(toFileName, data);
    }

    private String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            List<String> lines = new ArrayList<>();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createReport(String[] lines) {
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] operationDate = line.split(DELIMITER);
            String operation = operationDate[INDEX_OPERATION];
            int quantity = Integer.parseInt(operationDate[INDEX_QUANTITY]);
            if (operation.equals("supply")) {
                supply += quantity;
            } else {
                buy += quantity;
            }
        }
        int result = supply - buy;
        StringBuilder data = new StringBuilder("supply,"
                + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result);
        return data.toString();
    }

    private void writeFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
