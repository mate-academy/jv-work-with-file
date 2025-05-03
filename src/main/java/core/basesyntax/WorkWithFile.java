package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_AMOUNT_INDEX = 0;
    private static final int BUY_AMOUNT_INDEX = 1;
    private static final String SPECIFIED_CHARACTER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String OPERATION_RESULT_TITLE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readStatisticData(fromFileName);
        int[] statisticData = parseSourceDataString(fileData);
        String report = createReport(statisticData);
        writeFile(toFileName, report);
    }

    private String readStatisticData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            do {
                line = reader.readLine();
                if (line != null) {
                    stringBuilder.append(line).append(LINE_SEPARATOR);
                }
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private int[] parseSourceDataString(String dataString) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] lineArray = dataString.split(LINE_SEPARATOR);
        for (String line: lineArray) {
            String[] dataRow = line.split(SPECIFIED_CHARACTER);
            if (dataRow[OPERATION_TYPE_INDEX].equals(OperationType.SUPPLY.name().toLowerCase())) {
                supplyAmount += Integer.parseInt(dataRow[AMOUNT_INDEX]);
            }
            if (dataRow[OPERATION_TYPE_INDEX].equals(OperationType.BUY.name().toLowerCase())) {
                buyAmount += Integer.parseInt(dataRow[AMOUNT_INDEX]);
            }
        }
        return new int [] {supplyAmount, buyAmount};
    }

    private String createReport(int[] statisticData) {
        StringBuilder builder = new StringBuilder();
        return builder
                    .append(OperationType.SUPPLY.name().toLowerCase())
                    .append(SPECIFIED_CHARACTER)
                    .append(statisticData[SUPPLY_AMOUNT_INDEX])
                    .append(LINE_SEPARATOR)
                    .append(OperationType.BUY.name().toLowerCase())
                    .append(SPECIFIED_CHARACTER)
                    .append(statisticData[BUY_AMOUNT_INDEX])
                    .append(LINE_SEPARATOR)
                    .append(OPERATION_RESULT_TITLE)
                    .append(SPECIFIED_CHARACTER)
                    .append((statisticData[SUPPLY_AMOUNT_INDEX] - statisticData[BUY_AMOUNT_INDEX]))
                    .toString();
    }

    private void writeFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
