package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String RESULT_KEY = "result";
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String CSV_SEPARATOR = ",";
    private static final String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String[]> parsedCsv = parseCsv(fromFileName);
        int[] totals = getTotals(parsedCsv);
        String report = generateReport(totals);
        saveReport(report, toFileName);
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
            throw new RuntimeException("Error: Can't read file " + fromFileName, e);
        }
        return parsedCsv;
    }

    private int[] getTotals(List<String[]> parsedCsv) {
        int[] totals = new int[2];

        for (String[] parsedLine : parsedCsv) {
            if (SUPPLY_KEY.equals(parsedLine[OPERATION_INDEX])) {
                totals[SUPPLY_INDEX] += Integer.parseInt(parsedLine[AMOUNT_INDEX]);
            } else if (BUY_KEY.equals(parsedLine[OPERATION_INDEX])) {
                totals[BUY_INDEX] += Integer.parseInt(parsedLine[AMOUNT_INDEX]);
            }
        }

        return totals;
    }

    private String generateReport(int[] totals) {
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder.append(SUPPLY_KEY)
                .append(CSV_SEPARATOR)
                .append(totals[SUPPLY_INDEX])
                .append(NEW_LINE);
        reportBuilder.append(BUY_KEY)
                .append(CSV_SEPARATOR)
                .append(totals[BUY_INDEX])
                .append(NEW_LINE);
        reportBuilder.append(RESULT_KEY)
                .append(CSV_SEPARATOR)
                .append(totals[SUPPLY_INDEX] - totals[BUY_INDEX]);

        return reportBuilder.toString();
    }

    private void saveReport(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error: Can't write to file " + toFileName, e);
        }
    }
}
