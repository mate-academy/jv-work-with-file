package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY_ROW_NAME = "supply";
    public static final String BUY_ROW_NAME = "buy";
    public static final String RESULT_ROW_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataRows = readData(fromFileName);
        String resultString = processData(dataRows);
        writeReport(toFileName, resultString);
    }

    private List<String> readData(String sourceFile) {
        try {
            return Files.readAllLines(new File(sourceFile).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String processData(List<String> data) {
        int supplySum = 0;
        int buySum = 0;
        for (String row : data) {
            String[] rowDataArr = row.split(",");
            if (rowDataArr[0].equals(SUPPLY_ROW_NAME)) {
                supplySum += Integer.parseInt(rowDataArr[1]);
            } else if (rowDataArr[0].equals(BUY_ROW_NAME)) {
                buySum += Integer.parseInt(rowDataArr[1]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(SUPPLY_ROW_NAME)
                .append(",")
                .append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_ROW_NAME)
                .append(",")
                .append(buySum)
                .append(System.lineSeparator())
                .append(RESULT_ROW_NAME)
                .append(",")
                .append(result);
        return stringBuilder.toString();
    }

    private void writeReport(String outputFile, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
