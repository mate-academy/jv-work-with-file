package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String OPERATION_VALUE_DELIMITER = ",";
    public static final String SUPPLY_OPERATION_TITLE = "supply";
    public static final String BUY_OPERATION_TITLE = "buy";
    public static final String RESULT_OPERATION_TITLE = "result";
    public static final int OPERATION_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(composeReport(calculateOperations(readLinesFromFile(fromFileName))), toFileName);
    }

    public String[] readLinesFromFile(String filename) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String string = bufferedReader.readLine();
            while (string != null) {
                stringBuilder.append(string).append(System.lineSeparator());
                string = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + filename, e);
        }
        return String.valueOf(stringBuilder).split(System.lineSeparator());
    }

    public Table calculateOperations(String[] lines) {
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
                default: break;
            }
        }
        return new Table(buy, supply);
    }

    public String composeReport(Table table) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_OPERATION_TITLE)
                .append(OPERATION_VALUE_DELIMITER)
                .append(table.getSupply())
                .append(System.lineSeparator())
                .append(BUY_OPERATION_TITLE)
                .append(OPERATION_VALUE_DELIMITER)
                .append(table.getBuy())
                .append(System.lineSeparator())
                .append(RESULT_OPERATION_TITLE)
                .append(OPERATION_VALUE_DELIMITER)
                .append(table.calculateResult());
        return String.valueOf(report);
    }

    public void writeFile(String report, String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + filename, e);
        }
    }
}
