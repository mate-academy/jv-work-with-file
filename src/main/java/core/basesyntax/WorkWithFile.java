package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_STRING = "result";
    private static final String SEPARATOR = ",";
    private static final int TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = parseData(getData(fromFileName));
        String report = createReport(data);
        writeReport(toFileName, report);
    }

    private List<String> getData(String fileName) {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return allLines;
    }

    private void writeReport(String fileName, String report) {
        try {
            Files.write(Path.of(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }

    private String createReport(int[] data) {
        int result = data[0] - data[1];
        return SUPPLY_OPERATION + SEPARATOR + data[0] + System.lineSeparator()
                + BUY_OPERATION + SEPARATOR + data[1] + System.lineSeparator()
                + RESULT_STRING + SEPARATOR + result;
    }

    private int[] parseData(List<String> data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : data) {
            String[] typeAndAmount = line.split(SEPARATOR);
            if (typeAndAmount[TYPE_INDEX].equals(SUPPLY_OPERATION)) {
                supplyAmount += Integer.parseInt(typeAndAmount[AMOUNT_INDEX]);
            } else if (typeAndAmount[TYPE_INDEX].equals(BUY_OPERATION)) {
                buyAmount += Integer.parseInt(typeAndAmount[AMOUNT_INDEX]);
            }
        }
        return new int[]{supplyAmount, buyAmount};
    }
}
