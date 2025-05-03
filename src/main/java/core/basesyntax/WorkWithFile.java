package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_VALUE_DELIMITER = ",";
    private static final String SUPPLY_OPERATION_TITLE = "supply";
    private static final String BUY_OPERATION_TITLE = "buy";
    private static final String RESULT_OPERATION_TITLE = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readLinesFromFileToArray(fromFileName);
        Table totals = createTableWithTotals(linesFromFile);
        String report = composeReport(totals);
        writeFile(report, toFileName);
    }

    private String[] readLinesFromFileToArray(String filename) {
        StringBuilder linesFromFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String string = bufferedReader.readLine();
            while (string != null) {
                linesFromFile.append(string).append(System.lineSeparator());
                string = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found or can't read from file " + filename, e);
        }
        return String.valueOf(linesFromFile).split(System.lineSeparator());
    }

    private Table createTableWithTotals(String[] lines) {
        int buy = 0;
        int supply = 0;
        for (String operation : lines) {
            String[] operationData = operation.split(OPERATION_VALUE_DELIMITER);
            switch (operationData[OPERATION_INDEX]) {
                case BUY_OPERATION_TITLE: {
                    buy += Integer.parseInt(operationData[VALUE_INDEX]);
                    break;
                }
                case SUPPLY_OPERATION_TITLE: {
                    supply += Integer.parseInt(operationData[VALUE_INDEX]);
                    break;
                }
                default:
                    break;
            }
        }
        return new Table(buy, supply);
    }

    private String composeReport(Table totals) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_OPERATION_TITLE)
                .append(OPERATION_VALUE_DELIMITER)
                .append(totals.getSupply())
                .append(System.lineSeparator())
                .append(BUY_OPERATION_TITLE)
                .append(OPERATION_VALUE_DELIMITER)
                .append(totals.getBuy())
                .append(System.lineSeparator())
                .append(RESULT_OPERATION_TITLE)
                .append(OPERATION_VALUE_DELIMITER)
                .append(totals.calculateResult());
        return report.toString();
    }

    private void writeFile(String report, String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + filename, e);
        }
    }
}
