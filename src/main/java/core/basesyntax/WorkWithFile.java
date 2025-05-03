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
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    public String readFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
        return fileContent.toString();
    }

    public String createReport(String output) {
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder builder = new StringBuilder();
        String[] data = output.split(System.lineSeparator());
        for (String part : data) {
            String[] parts = part.split(COMMA_SEPARATOR);
            String operationType = parts[OPERATION_TYPE_INDEX].trim();
            int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
            if (SUPPLY.equals(operationType)) {
                totalSupply += amount;
            }
            if (BUY.equals(operationType)) {
                totalBuy += amount;
            }
        }
        String supply = SUPPLY + COMMA_SEPARATOR + totalSupply + System.lineSeparator();
        String buy = BUY + COMMA_SEPARATOR + totalBuy
                + System.lineSeparator();
        String result = RESULT + COMMA_SEPARATOR + (totalSupply - totalBuy);
        return builder.append(supply).append(buy).append(result).toString();
    }

    public void writeToFile(String report, String toFileName) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFileName, false))) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file.", e);
        }
    }
}
