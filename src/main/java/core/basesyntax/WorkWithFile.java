package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String CSV_SPLIT_BY = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final String RESULTING_ROW_NAME = "result";
    private static final String BUY_ROW_NAME = "buy";
    private static final String SUPPLY_ROW_NAME = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        var linesFromCsvFile = readLinesFromCsv(fromFileName);
        String report = createReport(linesFromCsvFile);
        writeStatToFile(report, toFileName);
    }

    private String createReport(List<String> linesFromCsvFile) {
        int totalAmountSupply = 0;
        int totalAmountBuy = 0;
        for (String dataLine : linesFromCsvFile) {
            Operation operation = splitCvs(dataLine);
            if (SUPPLY_ROW_NAME.equals(operation.type())) {
                totalAmountSupply += operation.amount();
            } else if (BUY_ROW_NAME.equals(operation.type())) {
                totalAmountBuy += operation.amount();
            } else {
                throw new RuntimeException("Error while reading field from CSV");
            }
        }
        return formCsvLine(SUPPLY_ROW_NAME, totalAmountSupply)
                + formCsvLine(BUY_ROW_NAME, totalAmountBuy)
                + formCsvLine(RESULTING_ROW_NAME, totalAmountSupply - totalAmountBuy);
    }

    public void writeStatToFile(String report, String toFile) {
        try (FileWriter clearFileWriter = new FileWriter(toFile, false)) {
            clearFileWriter.write(""); // To clear file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readLinesFromCsv(String csvFilePath) {
        try {
            return Files.readAllLines(Path.of(csvFilePath));
        } catch (IOException e) {
            throw new RuntimeException("DevCap: Exception while reading from CVS file -- ", e);
        }
    }

    public String formCsvLine(String type, int amount) {
        // System.lineSeparator()
        return type + CSV_SPLIT_BY + amount + System.lineSeparator();
    }

    public Operation splitCvs(String csvLine) {
        String[] splitCvsLine = csvLine.split(CSV_SPLIT_BY);
        return new Operation(splitCvsLine[OPERATION_TYPE_INDEX],
                Integer.parseInt(splitCvsLine[OPERATION_AMOUNT_INDEX]));
    }
}
