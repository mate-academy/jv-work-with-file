package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIFIED_STRING_S = "supply";
    private static final String SPECIFIED_STRING_B = "buy";
    private static final String SPECIFIED_STRING_R = "result";
    private static final String SEPARATOR = ",";
    private static final int VALUE_ONE = 0;
    private static final int VALUE_TWO = 1;
    private static final int VALUE_THREE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data.toString();
    }

    private String generateReport(String data) {
        int buyTotal = 0;
        int supplyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] values = line.split(SEPARATOR);
            if (values.length == VALUE_THREE) {
                String operation = values[VALUE_ONE];
                int amount = Integer.parseInt(values[VALUE_TWO]);
                if (SPECIFIED_STRING_S.equals(operation)) {
                    supplyTotal += amount;
                } else if (SPECIFIED_STRING_B.equals(operation)) {
                    buyTotal += amount;
                }
            }
        }
        int result = supplyTotal - buyTotal;
        return SPECIFIED_STRING_S + SEPARATOR + supplyTotal
                + "\n" + SPECIFIED_STRING_B + SEPARATOR + buyTotal
                + "\n" + SPECIFIED_STRING_R + SEPARATOR + result;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
