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
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] reportData = readFile(fromFileName);
        String report = calculateReport(reportData);
        writeToFile(toFileName, report);
    }

    private int[] readFile(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                String[] splitLine = value.split(COMMA);
                String name = splitLine[0];
                int amount = Integer.parseInt(splitLine[1]);

                if (SUPPLY.equals(name)) {
                    supply += amount;
                } else if (BUY.equals(name)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read a file", e);
        }

        return new int[]{supply, buy};
    }

    private String calculateReport(int[] reportData) {
        int supplyOutput = reportData[0];
        int buyOutput = reportData[1];
        int result = supplyOutput - buyOutput;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMMA).append(supplyOutput).append(System.lineSeparator());
        report.append(BUY).append(COMMA).append(buyOutput).append(System.lineSeparator());
        report.append(RESULT).append(COMMA).append(result);

        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to the file", e);
        }
    }
}
