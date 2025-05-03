package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX_ZERO = 0;
    private static final int OPERATION_INDEX_ONE = 1;
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readFromFile(fromFileName));
        writeReportToFile(toFileName, report);
    }

    private String createReport(List<String> listData) {
        StringBuilder strResult = new StringBuilder();
        int supplyResult = 0;
        int buyResult = 0;
        int sumResult;
        for (String listDatum : listData) {
            String[] lineData = listDatum.split(COMMA);
            if (lineData[OPERATION_INDEX_ZERO].equals(SUPPLY)) {
                supplyResult += Integer.parseInt(lineData[OPERATION_INDEX_ONE]);
            }
            if (lineData[OPERATION_INDEX_ZERO].equals(BUY)) {
                buyResult += Integer.parseInt(lineData[OPERATION_INDEX_ONE]);
            }
        }
        sumResult = supplyResult - buyResult;
        return strResult.append(SUPPLY + COMMA).append(supplyResult)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyResult).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(sumResult).toString();
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can,t read file" + file.getName(), e);
        }
        return lines;
    }

    private void writeReportToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + file.getName(), e);
        }
    }
}
