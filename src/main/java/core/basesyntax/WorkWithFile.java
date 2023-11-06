package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String NEW_LINE = System.lineSeparator();
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileContent = readFromFile(fromFileName);
        String report = buildReportResult(fileContent);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read form file " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String buildReportResult(List<String> fileContent) {
        StringBuilder builder = new StringBuilder("supply,");
        int supplyResult = 0;
        int buyResult = 0;
        for (String dataElement : fileContent) {
            String[] splitElement = dataElement.split(",");
            if (splitElement[OPERATION_TYPE_INDEX].equals("supply")) {
                supplyResult += Integer.parseInt(splitElement[AMOUNT_INDEX]);
            }
            if (splitElement[OPERATION_TYPE_INDEX].equals("buy")) {
                buyResult += Integer.parseInt(splitElement[AMOUNT_INDEX]);
            }
        }
        int result = supplyResult - buyResult;
        return builder.append(supplyResult).append(NEW_LINE)
                .append("buy,").append(buyResult).append(NEW_LINE)
                .append("result,").append(result).toString();
    }
}
