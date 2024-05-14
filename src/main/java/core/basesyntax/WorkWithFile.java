package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String result = createReport(dataFromFile);
        writeReportToFile(result, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String bufferValue = bf.readLine();
            while (bufferValue != null) {
                result.append(bufferValue).append(System.lineSeparator());
                bufferValue = bf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fileName, e);
        }
        return result.toString();
    }

    private String createReport(String dataFromFile) {
        int supplyResult = 0;
        int buyResult = 0;
        int result;
        for (String string: dataFromFile.split("\\s+")) {
            if (string.contains(SUPPLY)) {
                supplyResult += Integer.parseInt(string.split(COMMA)[1]);
            } else {
                buyResult += Integer.parseInt(string.split(COMMA)[1]);
            }
        }
        result = supplyResult - buyResult;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supplyResult).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyResult).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }

    private void writeReportToFile(String report, String toFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFile))) {
            bw.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFile, e);
        }
    }
}
