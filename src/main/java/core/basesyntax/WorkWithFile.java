package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_POSITION = 0;
    private static final int NUMBER_POSITION = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeReport(resultCalculation(readFile(fromFileName)), toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String resultCalculation(String dataFromFile) {
        String[] stringArray = dataFromFile.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String oneLine : stringArray) {
            String[] categoryAndPrice = oneLine.split(SEPARATOR);
            if (categoryAndPrice[NAME_POSITION].equals(SUPPLY)) {
                supplySum += Integer.parseInt(categoryAndPrice[NUMBER_POSITION]);
            } else if (categoryAndPrice[NAME_POSITION].equals(BUY)) {
                buySum += Integer.parseInt(categoryAndPrice[NUMBER_POSITION]);
            }
        }
        return generateReport(supplySum, buySum);
    }

    private String generateReport(int supplySum, int buySum) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY)
                .append(SEPARATOR)
                .append(supplySum)
                .append(System.lineSeparator())
                .append(BUY)
                .append(SEPARATOR)
                .append(buySum)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(SEPARATOR)
                .append(supplySum - buySum);
        return stringBuilder.toString();
    }

    private void writeReport(String data, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + report, e);
        }
    }
}
