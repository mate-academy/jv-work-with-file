package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY_IDENTIFICATION = "supply";
    private static final String BUY_IDENTIFICATION = "buy";
    private static final String CSV_DIVIDER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        TransactionData transactionData = readAndCalculatedFromFile(fromFileName);

        writeStatisticsToFile(toFileName, transactionData);
    }

    private TransactionData readAndCalculatedFromFile(String fileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(CSV_DIVIDER);
                if (array.length == 2) {
                    String type = array[0].trim();
                    int value = Integer.parseInt(array[1].trim());

                    if (type.equals(SUPPLY_IDENTIFICATION)) {
                        supply += value;
                    } else if (type.equals(BUY_IDENTIFICATION)) {
                        buy += value;
                    }
                }
                result = supply - buy;
            }

            return new TransactionData(supply, buy, result);

        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private void writeStatisticsToFile(String toFile, TransactionData result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(convertResult(result));
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }

    private String convertResult(TransactionData transactionData) {
        StringBuilder resultsBuilder = new StringBuilder();

        return resultsBuilder.append("supply,").append(transactionData.supply)
            .append(System.lineSeparator())
            .append("buy,").append(transactionData.buy).append(System.lineSeparator())
            .append("result,").append(transactionData.result).toString();
    }
}
