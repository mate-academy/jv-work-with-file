package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String DIVIDER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String value = readFile(fromFileName);
        String report = createReport(value);
        fileWrite(toFileName, report);
    }

    public static String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(DIVIDER);
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private static String createReport(String value) {
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;
        String[] data = value.split(DIVIDER);
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(SUPPLY)) {
                supplyResult += Integer.parseInt(data[i + 1]);
            } else if (data[i].equals(BUY)) {
                buyResult += Integer.parseInt(data[i + 1]);
            }
            result = supplyResult - buyResult;
        }
        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder report = resultBuilder.append(SUPPLY).append(DIVIDER).append(supplyResult)
                .append(SEPARATOR)
                .append(BUY).append(DIVIDER).append(buyResult)
                .append(SEPARATOR)
                .append("result").append(DIVIDER).append(result);
        return String.valueOf(report);
    }

    private static void fileWrite(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(String.valueOf(report));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}

