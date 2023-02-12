package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WHITESPACE_CHARACTER = " ";
    private static final String COMMA_CHARACTER = ",";
    private static final String BUY_STRING = "buy";
    private static final String SUPPLY_STRING = "supply";
    private static final String RESULT_STRING = "result";
    private static final String EMPTY_STRING = "";

    public void updateFile(String path) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
            fileWriter.write(EMPTY_STRING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close resources");
            }
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        updateFile(toFileName);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(WHITESPACE_CHARACTER);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data", e);
        }
        String[] arrayOfData = builder.toString().split(WHITESPACE_CHARACTER);
        int counterBuy = 0;
        int counterSupply = 0;
        int result;
        for (int i = 0; i < arrayOfData.length; i++) {
            String[] temp = arrayOfData[i].split(COMMA_CHARACTER);
            if (temp[0].equals(SUPPLY_STRING)) {
                counterSupply = counterSupply + Integer.parseInt(temp[1]);
            } else {
                counterBuy = counterBuy + Integer.parseInt(temp[1]);
            }
        }
        result = counterBuy < counterSupply ? counterSupply - counterBuy
                : counterBuy - counterSupply;
        try {
            writer = new BufferedWriter(new FileWriter(toFileName, true));
            writer.write(SUPPLY_STRING + COMMA_CHARACTER + counterSupply
                    + System.lineSeparator() + BUY_STRING + COMMA_CHARACTER
                    + counterBuy + System.lineSeparator() + RESULT_STRING
                    + COMMA_CHARACTER + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close resources", e);
            }
        }
    }
}
