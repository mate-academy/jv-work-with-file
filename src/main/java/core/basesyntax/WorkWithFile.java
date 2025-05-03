package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIFIED_CHARACTER = ",";
    private static final String TYPE_SUPPLY = "supply";
    private static final String TYPE_BUY = "buy";
    private static final String TYPE_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String result = processData(dataFromFile);
        writeToFile(toFileName, result);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        return builder.toString();
    }

    private String processData(String dataFromFile) {
        String[] lines = dataFromFile.split(" ");
        StringBuilder result = new StringBuilder();
        int amountOfSupply = 0;
        int amountOfBuy = 0;
        for (int i = 0; i < lines.length; i++) {
            String[] dataOnLine = lines[i].split(SPECIFIED_CHARACTER);
            if (dataOnLine[0].equals(TYPE_SUPPLY)) {
                amountOfSupply += Integer.parseInt(dataOnLine[1]);
            }
            if (dataOnLine[0].equals(TYPE_BUY)) {
                amountOfBuy += Integer.parseInt(dataOnLine[1]);
            }
        }
        int finalAmount = amountOfSupply - amountOfBuy;
        result = result.append(TYPE_SUPPLY + SPECIFIED_CHARACTER
                        + amountOfSupply + System.lineSeparator())
                .append(TYPE_BUY + SPECIFIED_CHARACTER
                        + amountOfBuy + System.lineSeparator())
                .append(TYPE_RESULT + SPECIFIED_CHARACTER + finalAmount);
        return result.toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file " + toFileName, e);
        }
    }
}
