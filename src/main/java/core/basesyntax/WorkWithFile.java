package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;
    private static final String DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] inputData = readData(fromFileName);
        String report = createReport(inputData);
        writeResult(toFileName, report);
    }

    private int[] readData(String fromFile) {
        int[] statistic = new int[OperationType.values().length];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String fileLine = bufferedReader.readLine();
            if (fileLine == null) {
                return new int[]{};
            }
            while (fileLine != null) {
                String[] data = fileLine.split(DATA_SEPARATOR);
                OperationType operation = OperationType.valueOf(data[OPERATION].toUpperCase());
                switch (operation) {
                    case SUPPLY:
                        statistic[OperationType.SUPPLY.ordinal()] += Integer.parseInt(data[AMOUNT]);
                        break;
                    default:
                        statistic[OperationType.BUY.ordinal()] += Integer.parseInt(data[AMOUNT]);
                        break;
                }
                fileLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFile, e);
        }
        return statistic;
    }

    private String createReport(int[] statistic) {
        statistic[OperationType.RESULT.ordinal()]
                = statistic[OperationType.SUPPLY.ordinal()]
                - statistic[OperationType.BUY.ordinal()];
        StringBuilder stringBuilder = new StringBuilder();
        for (OperationType operation : OperationType.values()) {
            stringBuilder.append(operation.toString().toLowerCase())
                    .append(DATA_SEPARATOR)
                    .append(statistic[operation.ordinal()])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private void writeResult(String toFileName, String report) {
        try (FileWriter fileWriter = new FileWriter(new File(toFileName))) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file: " + toFileName);
        }
    }
}
