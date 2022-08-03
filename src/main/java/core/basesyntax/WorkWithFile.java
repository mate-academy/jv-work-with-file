package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DATA_VALUE = 1;
    private static final String DELIMETR = ",";
    private static final String NEW_LINE_DELIMETR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        File outputFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(readFile(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file:" + toFileName, e);
        }
    }

    public String readFile(String fromFileName) {
        File inputFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        int supplyValue = 0;
        int buyValue = 0;
        String[] data;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file:" + fromFileName, e);
        }
        return calculateResult(builder.toString());
    }

    public String calculateResult(String data) {
        StringBuilder builder = new StringBuilder();
        int supplyValue = 0;
        int buyValue = 0;
        String[] dataArr = data.split(System.lineSeparator());
        for (String string : dataArr) {
            if (string.contains("supply")) {
                supplyValue += Integer.parseInt(string
                        .substring(string.indexOf(DELIMETR) + DATA_VALUE));
            }
            if (string.contains("buy")) {
                buyValue += Integer.parseInt(string
                        .substring(string.indexOf(DELIMETR) + DATA_VALUE));
            }
        }
        builder.append("supply,").append(supplyValue).append(System.lineSeparator())
                .append("buy,").append(buyValue).append(System.lineSeparator())
                .append("result,").append(supplyValue - buyValue);
        return builder.toString();
    }
}
