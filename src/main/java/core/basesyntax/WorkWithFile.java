package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final int OPERATION_AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        var dataFromFile = readFromFile(fromFileName);
        var report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException ex) {
            throw new RuntimeException("Can`t read data from the file "
                    + fromFileName, ex);
        }
    }

    private String createReport(List<String> data) {
        var report = new StringBuilder();
        var supplyAmount = countTotalAmount(data, OPERATION_TYPE_SUPPLY);
        var buyAmount = countTotalAmount(data, OPERATION_TYPE_BUY);
        report.append(OPERATION_TYPE_SUPPLY)
                .append(COMMA)
                .append(supplyAmount)
                .append(LINE_SEPARATOR)
                .append(OPERATION_TYPE_BUY)
                .append(COMMA)
                .append(buyAmount)
                .append(LINE_SEPARATOR)
                .append(RESULT)
                .append(COMMA)
                .append(supplyAmount - buyAmount);
        return report.toString();
    }

    private int countTotalAmount(List<String> data, String operationType) {
        return data.stream()
                .filter(s -> s.contains(operationType))
                .map(s -> s.split(COMMA))
                .mapToInt(strings -> Integer.parseInt(strings[OPERATION_AMOUNT_INDEX]))
                .sum();
    }

    private void writeToFile(String report, String toFileName) {
        try (var bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName),
                StandardCharsets.UTF_8)) {
            bufferedWriter.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can`t write data to the file "
                    + toFileName, ex);
        }
    }
}
