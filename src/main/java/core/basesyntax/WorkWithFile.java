package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String RESULT_KEY = "result";
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String CSV_SEPARATOR = ",";
    private static final String NEW_LINE = System.lineSeparator();

    private int supplyTotal = 0;
    private int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String[]> parsedCsv = parseCsv(fromFileName);
        calculateTotals(parsedCsv);
        saveReport(generateReport(), toFileName);
    }

    private List<String[]> parseCsv(String fromFileName) {
        List<String[]> parsedCsv = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                parsedCsv.add(line.split(CSV_SEPARATOR));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: Can't read file " + fromFileName + ".", e);
        }
        return parsedCsv;
    }

    private void calculateTotals(List<String[]> parsedCsv) {
        supplyTotal = 0;
        buyTotal = 0;

        for (String[] parsedLine : parsedCsv) {
            if (SUPPLY_KEY.equals(parsedLine[INDEX_OF_OPERATION])) {
                supplyTotal += Integer.parseInt(parsedLine[INDEX_OF_AMOUNT]);
            } else if (BUY_KEY.equals(parsedLine[INDEX_OF_OPERATION])) {
                buyTotal += Integer.parseInt(parsedLine[INDEX_OF_AMOUNT]);
            }
        }
    }

    private String generateReport() {
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder.append(SUPPLY_KEY)
                .append(CSV_SEPARATOR)
                .append(supplyTotal)
                .append(NEW_LINE);
        reportBuilder.append(BUY_KEY)
                .append(CSV_SEPARATOR)
                .append(buyTotal)
                .append(NEW_LINE);
        reportBuilder.append(RESULT_KEY)
                .append(CSV_SEPARATOR)
                .append(supplyTotal - buyTotal);

        return reportBuilder.toString();
    }

    private void saveReport(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error: Can't write to file " + toFileName + ".", e);
        }
    }
}
