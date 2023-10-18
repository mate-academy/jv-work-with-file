package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIFIED_STRING_S = "supply";
    private static final String SPECIFIED_STRING_B = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String data = readFile(fromFileName);
            String report = generateReport(data);
            writeToFile(toFileName, report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String fromFileName) throws IOException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append("\n");
            }
        }
        return data.toString();
    }

    private String generateReport(String data) {
        int buyTotal = 0;
        int supplyTotal = 0;
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] values = line.split(",");
            if (values.length == 2) {
                String operation = values[0];
                int amount = Integer.parseInt(values[1]);
                if (SPECIFIED_STRING_S.equals(operation)) {
                    supplyTotal += amount;
                } else if (SPECIFIED_STRING_B.equals(operation)) {
                    buyTotal += amount;
                }
            }
        }
        int result = supplyTotal - buyTotal;
        return "supply," + supplyTotal + "\nbuy," + buyTotal + "\nresult," + result;
    }

    private void writeToFile(String toFileName, String report) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        }
    }
}
