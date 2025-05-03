package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeFile(toFileName, report);
    }

    public static List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> data = new ArrayList<>();
            String value = reader.readLine();
            while (value != null) {
                data.add(value);
                value = reader.readLine();
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
    }

    public static String generateReport(List<String> data) {
        int supplyResult = 0;
        int buyResult = 0;
        for (String line : data) {
            String[] values = line.split(",");
            if (values[FIRST].equals(SUPPLY)) {
                supplyResult += Integer.parseInt(values[SECOND]);
            } else if (values[FIRST].equals(BUY)) {
                buyResult += Integer.parseInt(values[SECOND]);
            }
        }
        int finalResult = supplyResult - buyResult;
        StringBuilder resultString = new StringBuilder();
        resultString.append(SUPPLY).append(",").append(supplyResult).append("\n")
                .append(BUY).append(",").append(buyResult).append("\n")
                .append(RESULT).append(",").append(finalResult).append("\n");
        return resultString.toString();
    }

    private static void writeFile(String toFileName, String result) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }
}
