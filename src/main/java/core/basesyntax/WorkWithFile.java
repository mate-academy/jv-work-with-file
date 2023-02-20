package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIFIED_CHARACTER = ",";
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_RESULT = "result";
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        readFile(fromFileName, builder);
        String[] lines = builder.toString().split(" ");
        StringBuilder result = new StringBuilder();
        processData(lines, result);
        writeToFile(toFileName, result.toString());
    }

    private void readFile(String fromFileName, StringBuilder builder) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value + " ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file " + toFileName, e);
        }
    }

    private void processData(String[] lines, StringBuilder result) {
        int ammountOfSupply = 0;
        int ammountOfBuy = 0;
        for (int i = 0; i < lines.length; i++) {
            String[] dataOnTheLine = lines[i].split(SPECIFIED_CHARACTER);
            if (dataOnTheLine[INDEX_OF_OPERATION_TYPE].equals(OPERATION_TYPE_SUPPLY)) {
                ammountOfSupply += Integer.parseInt(dataOnTheLine[INDEX_OF_AMMOUNT]);
            } else if (dataOnTheLine[INDEX_OF_OPERATION_TYPE].equals(OPERATION_TYPE_BUY)) {
                ammountOfBuy += Integer.parseInt(dataOnTheLine[INDEX_OF_AMMOUNT]);
            }
        }
        int resultOfAmmount = ammountOfSupply - ammountOfBuy;
        result.append(OPERATION_TYPE_SUPPLY + SPECIFIED_CHARACTER
                        + ammountOfSupply + System.lineSeparator())
                .append(OPERATION_TYPE_BUY + SPECIFIED_CHARACTER
                        + ammountOfBuy + System.lineSeparator())
                .append(OPERATION_TYPE_RESULT + SPECIFIED_CHARACTER + resultOfAmmount);
    }
}
