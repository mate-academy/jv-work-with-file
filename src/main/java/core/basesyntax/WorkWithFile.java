package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        StringBuilder builder = new StringBuilder();
        String[] line = readFile(String.valueOf(new File(fromFileName)));
        for (String lines : line) {
            String[] splittedLine = lines.split(",");
            if (splittedLine[OPERATION_INDEX].equals("supply")) {
                supplyCount += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            } else {
                buyCount += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
        }
        builder.append("supply").append(",").append(supplyCount).append(System.lineSeparator())
                .append("buy").append(",").append(buyCount).append(System.lineSeparator())
                .append("result").append(",").append(supplyCount - buyCount);
        writeReportFile(new File(toFileName), builder.toString());
    }

    private String[] readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                builder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void writeReportFile(File file, String statisitic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.write(statisitic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + statisitic, e);
        }
    }
}
