package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String inputContent = readData(fromFileName);
        String report = makeReport(inputContent);
        writeReport(report, toFileName);
    }

    private String readData(String filePath) {
        File inputFile = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));) {
            StringBuilder readBuilder = new StringBuilder();
            String readValue = reader.readLine();
            while (readValue != null) {
                readBuilder.append(readValue).append(System.lineSeparator());
                readValue = reader.readLine();
            }
            return readBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file", e);
        }
    }

    private String makeReport(String inputContent) {
        int supplyValue = 0;
        int buyValue = 0;
        String[] contentParts = inputContent.split(System.lineSeparator());
        for (String input : contentParts) {
            String[] inputParts = input.split(",");
            if (inputParts[KEY_INDEX].equals(SUPPLY_KEY)) {
                supplyValue += Integer.parseInt(inputParts[VALUE_INDEX]);
            } else if (inputParts[KEY_INDEX].equals(BUY_KEY)) {
                buyValue += Integer.parseInt(inputParts[VALUE_INDEX]);
            }
        }
        return "supply," + supplyValue + System.lineSeparator()
                + "buy," + buyValue + System.lineSeparator()
                + "result," + (supplyValue - buyValue);
    }

    private void writeReport(String report, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file.", e);
        }
    }
}
