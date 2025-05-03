package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_CHARACTER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int TRANSACTION_TYPE_INDEX = 0;
    private static final int TRANSACTION_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readCsvFile(fromFileName);
        String report = createReport(fileContent);
        writeToFile(report,toFileName);
    }

    private String readCsvFile(String fromFileName) {
        String line;
        StringBuilder stringFileContent = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringFileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringFileContent.toString();
    }

    private String createReport(String fileContent) {
        String[] transactions = fileContent.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String transaction : transactions) {
            String[] transactionDetails = transaction.split(SPLIT_CHARACTER);
            int transactionValue = Integer.parseInt(transactionDetails[TRANSACTION_VALUE_INDEX]);
            if (transactionDetails[TRANSACTION_TYPE_INDEX].equals(SUPPLY)) {
                supply += transactionValue;
            } else {
                buy += transactionValue;
            }
        }

        return new StringBuilder().append(SUPPLY)
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to file " + toFileName,e);
        }
    }
}
