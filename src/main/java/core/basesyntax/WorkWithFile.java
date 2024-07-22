package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textFromFile = readFromFile(fromFileName);
        String report = generateReport(textFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        try (FileReader file = new FileReader(fromFileName);
                BufferedReader reader = new BufferedReader(file)) {
            StringBuilder text = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                text.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            return text.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private String generateReport(String[] data) {
        StringBuilder report = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int result;
        for (String product : data) {
            String[] line = product.split(COMMA);
            if (line[0].equals(SUPPLY)) {
                supplySum += Integer.parseInt(line[1]);
            } else {
                buySum += Integer.parseInt(line[1]);
            }
        }
        result = supplySum - buySum;
        report.append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator());
        report.append(BUY).append(COMMA).append(buySum).append(System.lineSeparator());
        report.append(RESULT).append(COMMA).append(result);

        return report.toString();
    }

    private void writeToFile(String text, String toFile) {

        try (FileWriter file = new FileWriter(toFile);
                BufferedWriter writer = new BufferedWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
