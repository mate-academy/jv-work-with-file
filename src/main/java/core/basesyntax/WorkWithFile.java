package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String FIRST_OPERATION_TYPE_STRING = "supply";
    private static final String SECOND_OPERATION_TYPE_STRING = "buy";
    private static final String RESULT_STRING = "result";
    private static final String SEPARATOR = ",";

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
        return FIRST_OPERATION_TYPE_STRING + SEPARATOR + data[0] + System.lineSeparator()
                + SECOND_OPERATION_TYPE_STRING + SEPARATOR + data[1] + System.lineSeparator()
                + RESULT_STRING + SEPARATOR + result;
    }

    private int[] parseData(List<String> data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : data) {
            String[] typeAndAmount = line.split(SEPARATOR);
            if (typeAndAmount[0].equals(FIRST_OPERATION_TYPE_STRING)) {
                supplyAmount += Integer.parseInt(typeAndAmount[1]);
            } else {
                buyAmount += Integer.parseInt(typeAndAmount[1]);
            }
        }
        return new int[]{supplyAmount, buyAmount};
    }
}
