package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_PREFIX = "buy,";
    private static final String SUPPLY_PREFIX = "supply,";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        File writeFile = new File(toFileName);

        calculateReport(readFile, writeFile);
    }

    private void calculateReport(File readFile, File writeFile) {
        int buy = 0;
        int supply = 0;
        String[] fileInfoArr = readFromFile(readFile).split("[\\r\\n\\s]+");
        String[] infoArrCurrentIndex;

        for (String info : fileInfoArr) {
            infoArrCurrentIndex = info.split(",");
            if (info.startsWith(BUY_PREFIX)) {
                int buyIndex = Integer.parseInt(infoArrCurrentIndex[INDEX]);
                buy += buyIndex;
            } else if (info.startsWith(SUPPLY_PREFIX)) {
                int supplyIndex = Integer.parseInt(infoArrCurrentIndex[INDEX]);
                supply += supplyIndex;
            }
        }
        int result = supply - buy;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        writeToFile(reportBuilder, writeFile);
    }

    private String readFromFile(File readFile) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read the file", e);
        }
        return builder.toString();
    }

    private void writeToFile(StringBuilder reportBuilder, File writeFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
            writer.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file", e);
        }
    }
}
