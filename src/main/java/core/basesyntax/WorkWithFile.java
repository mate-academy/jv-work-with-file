package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] workData = readInputFile(fromFileName);
        String report = createReport(workData);
        writeReport(report, toFileName);
    }

    private String[] readInputFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can't be read", e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] workData) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String line : workData) {
            switch (line.substring(0, line.indexOf(','))) {
                case SUPPLY:
                    supply += Integer.valueOf(line.substring(line.indexOf(',') + 1));
                    break;
                case BUY:
                    buy += Integer.valueOf(line.substring(line.indexOf(',') + 1));
                    break;
                default:
                    break;
            }
        }
        result = supply - buy;
        StringBuilder builderForWrite = new StringBuilder();
        builderForWrite.append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT).append(",").append(result);
        return builderForWrite.toString();
    }

    private void writeReport(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("File can't be write", e);
        }
    }
}

