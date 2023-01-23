package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATE_SYMBOL = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] data = readFromFile(fromFileName);
        writeToFile(getReport(data), toFileName);
    }

    private String [] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t open file to read.", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t close file to read.", e);
        }
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getReport(String [] data) {
        int supplySum = 0;
        int buySum = 0;
        for (String record: data) {
            int value = Integer.parseInt(record.substring(record.indexOf(SEPARATE_SYMBOL) + 1));
            if (record.startsWith(SUPPLY)) {
                supplySum += value;
            }
            if (record.startsWith(BUY)) {
                buySum += value;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(SEPARATE_SYMBOL).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATE_SYMBOL).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATE_SYMBOL).append(supplySum - buySum).toString();
    }
}
