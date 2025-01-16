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
    private static final String COMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = getReport(data);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String getReport(String data) {
        int countSupply = 0;
        int countBuy = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMA);
            if (parts.length == 2) {
                String operation = parts[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
                if (operation.equals(SUPPLY)) {
                    countSupply += amount;
                }
                if (operation.equals(BUY)) {
                    countBuy += amount;
                }
            }
        }

        int result = countSupply - countBuy;
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(COMA).append(countSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMA).append(countBuy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName,e);
        }
    }
}
