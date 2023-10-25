package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_CHARACTER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int TRANSACTION_TYPE_INDEX = 0;
    private static final int TRANSACTION_VALUE_INDEX = 1;
    private static final int SUPPLY_AMOUNT_INDEX = 0;
    private static final int BUY_AMOUNT_INDEX = 1;
    private static final int RESULT_AMOUNT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] transactionsValues = readCsvFile(fromFileName);
        String report = createReport(transactionsValues);
        writeToFile(report,toFileName);
    }

    private int[] readCsvFile(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction = line.split(SPLIT_CHARACTER);
                int transactionValue = Integer.parseInt(transaction[TRANSACTION_VALUE_INDEX]);
                if (transaction[TRANSACTION_TYPE_INDEX].equals(SUPPLY)) {
                    supply += transactionValue;
                } else {
                    buy += transactionValue;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return new int[] {supply, buy, supply - buy};
    }

    private String createReport(int[] transactionsValues) {
        return new StringBuilder().append(SUPPLY)
                .append(",")
                .append(transactionsValues[SUPPLY_AMOUNT_INDEX])
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(transactionsValues[BUY_AMOUNT_INDEX])
                .append(System.lineSeparator())
                .append("result,")
                .append(transactionsValues[RESULT_AMOUNT_INDEX]).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error while creating file " + toFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to file " + toFileName,e);
        }
    }
}
