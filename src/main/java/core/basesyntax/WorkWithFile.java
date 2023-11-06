package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WorkWithFile {
    private static final String csvSplitBy = ",";
    private static final int operationTypeIndex = 0;
    private static final int operationAmountIndex = 1;
    private static final String resultingRowName = "result";
    private static final String buyRowName = "buy";
    private static final String supplyRowName = "supply";
    HashMap<String, Integer> hashtable = new LinkedHashMap<>();
    private List<String> linesFromCsvFile;

    public void getStatistic(String fromFileName, String toFileName) {
        fillHashTable(fromFileName);
        calcResultRow();
        writeStatToFile(toFileName);
    }

    public void writeStatToFile(String toFile) {
        try (FileWriter clearFileWriter = new FileWriter(toFile, false)) {
            clearFileWriter.write(""); // To clear file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            iterateThoughHashTableRecords(bufferedWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void iterateThoughHashTableRecords(BufferedWriter bufferedWriter) throws IOException {
        for (String key : hashtable.keySet()) {
            int value = hashtable.get(key);
            String data = formCsvLine(key, value);
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        }
    }

    public void fillHashTable(String fromFile) {
        readLinesFromCsv(fromFile);
        constructHashTable();
        for (String cvsLine : linesFromCsvFile) {
            Operation operation = splitCvs(cvsLine);
            int previousAmountInCell = hashtable.get(operation.type());
            previousAmountInCell += operation.amount();
            hashtable.put(operation.type(), previousAmountInCell);
        }
    }

    public void readLinesFromCsv(String csvFilePath) {
        try {
            linesFromCsvFile = Files.readAllLines(Path.of(csvFilePath));
        } catch (IOException e) {
            throw new RuntimeException("DevCaption: -- Exception while reading from CVS file --", e);
        }
    }

    public void constructHashTable() {
        hashtable.put(supplyRowName, 0);
        hashtable.put(buyRowName, 0);
        hashtable.put(resultingRowName, 0);

//        for (String csvLine: linesFromCsvFile) {
//            Operation operation = splitCvs(csvLine);
//            if (!hashtable.contains(operation.type())) {
//                hashtable.put(operation.type(), 0);
//            }
//        }
    }

    public String formCsvLine(String type, int amount) {
        // System.lineSeparator()
        return type + csvSplitBy + amount;
    }

    public Operation splitCvs(String csvLine) {
        String[] splitCvsLine = csvLine.split(csvSplitBy);
        return new Operation(splitCvsLine[operationTypeIndex], Integer.parseInt(splitCvsLine[operationAmountIndex]));
    }

    public void calcResultRow() {
        int buySupplyDiff = hashtable.get(supplyRowName) - hashtable.get(buyRowName);
        hashtable.put(resultingRowName, buySupplyDiff);
    }
}
