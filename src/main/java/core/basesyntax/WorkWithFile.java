package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_VALUE_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INFO_TYPE_INDEX = 0;
    private static final int TYPE_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            Statistic statistic = readStatisticFromFile(fromFileName);
            writeStatisticToFile(statistic, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Error processing file " + fromFileName, e);
        }
    }

    private Statistic readStatisticFromFile(String fileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] oneLineData = line.split(",");
                if (oneLineData[INFO_TYPE_INDEX].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(oneLineData[TYPE_VALUE_INDEX]);
                } else {
                    buySum += Integer.parseInt(oneLineData[TYPE_VALUE_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fileName, e);
        }
        return new Statistic(supplySum, buySum);
    }

    private String generateReport(Statistic statistic) {
        return SUPPLY + DATA_VALUE_SEPARATOR + statistic.getSupplySum() + System.lineSeparator()
                + BUY + DATA_VALUE_SEPARATOR + statistic.getBuySum() + System.lineSeparator()
                + RESULT + DATA_VALUE_SEPARATOR + statistic.getResult();
    }

    private void writeStatisticToFile(Statistic statistic, String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(generateReport(statistic));
        } catch (IOException e) {
            throw new RuntimeException("Error writing file " + fileName, e);
        }
    }

    public static void main(String[] args) {
        Statistic statistic = new WorkWithFile().readStatisticFromFile("apple.csv");
        System.out.println(statistic.getSupplySum());
    }
}
