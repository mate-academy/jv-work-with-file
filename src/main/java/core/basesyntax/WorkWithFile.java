package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return data.toString();
    }

    private String createReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split(LINE_SEPARATOR);
        for (String line : lines) {
            String[] parts = line.split(",");
            if ("supply".equals(parts[OPERATION_TYPE_INDEX])) {
                supplyTotal += Integer.parseInt(parts[AMOUNT_INDEX]);
            } else if ("buy".equals(parts[OPERATION_TYPE_INDEX])) {
                buyTotal += Integer.parseInt(parts[AMOUNT_INDEX]);
            }
        }
        int result = supplyTotal - buyTotal;
        return "supply," + supplyTotal + LINE_SEPARATOR
               + "buy," + buyTotal + LINE_SEPARATOR
               + "result," + result;
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
