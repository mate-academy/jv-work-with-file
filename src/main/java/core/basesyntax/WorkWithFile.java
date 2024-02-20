package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int OPERATION_QUANTITY = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String COMMA = ",";
    private static final int SUPPLY_COUNTED = 0;
    private static final int BUY_COUNTED = 1;
    private static final int SUPPLY_BUY_DIFFERENCE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fromFileData = readFromFile(fromFileName);
        String report = makeReport(fromFileData);
        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from the file: " + fromFileName, e);
        }
    }

    private String makeReport(List<String> dataLines) {
        String[][] separatedData = separateData(dataLines);
        int[] buySupplyDiff = countOperations(separatedData);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_OPERATION)
                .append(COMMA)
                .append(buySupplyDiff[SUPPLY_COUNTED])
                .append(System.lineSeparator())
                .append(BUY_OPERATION)
                .append(COMMA)
                .append(buySupplyDiff[BUY_COUNTED])
                .append(System.lineSeparator())
                .append("result")
                .append(COMMA)
                .append(buySupplyDiff[SUPPLY_BUY_DIFFERENCE]);
        return stringBuilder.toString();
    }

    private String[][] separateData(List<String> dataLines) {
        String[][] separatedData = new String[dataLines.size()][];
        int counter = 0;
        for (String dataLine : dataLines) {
            separatedData[counter] = dataLine.split("\\p{Punct}");
            counter++;
        }
        return separatedData;
    }

    private int[] countOperations(String[][] separatedData) {
        int buy = 0;
        int supply = 0;
        int difference;
        for (String[] operation : separatedData) {
            if (operation[OPERATION_TYPE].equals(SUPPLY_OPERATION)) {
                supply += Integer.parseInt(operation[OPERATION_QUANTITY]);
            } else {
                buy += Integer.parseInt(operation[OPERATION_QUANTITY]);
            }
        }
        difference = supply - buy;
        return new int[]{supply, buy, difference};
    }

    private void writeToFile(String result, String toFileName) {
        try {
            Files.write(Path.of(toFileName), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
