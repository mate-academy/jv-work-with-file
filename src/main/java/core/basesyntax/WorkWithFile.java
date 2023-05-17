package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_NAME = "supply";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private final AccountingBook accountingBook = AccountingBook.getInstance();

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFile(fromFileName);
        writeFile(toFileName, report);
    }

    private String readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String record = bufferedReader.readLine();
            while (record != null) {
                analyzeRecord(record);
                record = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return getReport();
    }

    private void writeFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }

    private void analyzeRecord(String record) {
        String[] data = record.split(COMMA);
        String name = data[0];
        int amount = Integer.parseInt(data[1]);
        if (name.equals(SUPPLY_NAME)) {
            accountingBook.addSupply(amount);
        } else {
            accountingBook.addBuy(amount);
        }
    }

    private String getReport() {
        int supply = accountingBook.getSupply();
        int buy = accountingBook.getBuy();
        int difference = accountingBook.getSupply() - accountingBook.getBuy();
        accountingBook.erase();
        return "supply" + COMMA + supply + NEW_LINE
                + "buy" + COMMA + buy + NEW_LINE
                + "result" + COMMA + difference;
    }
}
