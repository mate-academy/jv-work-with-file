package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WorkWithFile {
    private static final char CSV_DELIMITER = ',';
    private static final String FILTER_FOR_DELIMITERS = "\\W+";
    private static final String SUPPLY_FILTER = "supply";
    private static final String BUY_FILTER = "buy";
    private static final String RESULT_FILTER = "result";
    private static final int RESET_STRING_BUILDER = 0;
    private static final int INDEX_OF_FIRST_ELEMENT = 0;
    private static final int INDEX_OF_SECOND_ELEMENT = 1;
    private static final int INDEX_OF_AMOUNT_ELEMENT = 1;
    private static final int QUANTITY_OF_OPERATIONS = 2;
    private final StringBuilder reportBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] temperedData = readFromFile(fromFileName);
        String report = makeReport(temperedData);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        Path fromFilePath = Paths.get(fromFileName);
        String seizedData;
        try {
            seizedData = Files.readString(fromFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file..." + fromFileName, e);
        }
        return seizedData.split(FILTER_FOR_DELIMITERS);
    }

    private String makeReport(String[] temperedData) {
        reportBuilder.setLength(RESET_STRING_BUILDER);
        ArrayList<String> tempArray = new ArrayList<>(QUANTITY_OF_OPERATIONS);
        tempArray.add(SUPPLY_FILTER);
        tempArray.add(BUY_FILTER);

        int supplySum = 0;
        int buySum = 0;

        for (int i = 0; i < temperedData.length; i++) {
            String operationType = temperedData[i];
            if (operationType.equals(tempArray.get(INDEX_OF_FIRST_ELEMENT))) {
                supplySum += Integer.parseInt(temperedData[i + INDEX_OF_AMOUNT_ELEMENT]);
            }
            if (operationType.equals(tempArray.get(INDEX_OF_SECOND_ELEMENT))) {
                buySum += Integer.parseInt(temperedData[i + INDEX_OF_AMOUNT_ELEMENT]);
            }
        }

        for (int i = 0; i < tempArray.size(); i++) {
            reportBuilder.append(tempArray.get(i)).append(CSV_DELIMITER);
            if (i == INDEX_OF_FIRST_ELEMENT) {
                reportBuilder.append(supplySum).append(System.lineSeparator());
            } else {
                reportBuilder.append(buySum).append(System.lineSeparator());
            }
        }
        reportBuilder.append(RESULT_FILTER).append(CSV_DELIMITER).append(supplySum - buySum);
        return reportBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        Path toFilePath = Paths.get(toFileName);
        try {
            Files.write(toFilePath, report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file..." + toFileName, e);
        }
    }
}
