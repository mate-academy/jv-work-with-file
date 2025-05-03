package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SLASH = "/";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        String[] arrayString = readDataFromFile(fromFile).split(SLASH);
        int supply = 0;
        int buy = 0;
        for (String string : arrayString) {
            String[] str = string.split(COMMA);
            if (str[0].equals(SUPPLY)) {
                supply += Integer.parseInt(str[1]);
            } else if (str[0].equals(BUY)) {
                buy += Integer.parseInt(str[1]);
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilderOut = new StringBuilder(createOutputString(supply, buy, result));
        writeDataToFile(toFileName, stringBuilderOut);
    }

    private String readDataFromFile(File fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SLASH);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read line from file: " + fromFile, e);
        }
        return stringBuilder.toString();
    }

    private String createOutputString(int supply, int buy, int result) {
        return SUPPLY + COMMA + supply
                + System.lineSeparator()
                + BUY + COMMA + buy
                + System.lineSeparator()
                + RESULT + COMMA + result
                + System.lineSeparator();
    }

    private void writeDataToFile(String toFileName, StringBuilder stringBuilderOut) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(stringBuilderOut.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write date to file: " + toFileName, e);
        }
    }
}
