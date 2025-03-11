package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String fruitStatistic = readFile(fromFileName);
        String report = createReport(fruitStatistic);
        writeFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String fruitStatistic) {
        final String delimiter = System.lineSeparator();
        final String recordSeparator = ",";
        final String supplyAction = "supply";
        final String buyAction = "buy";
        final String resultAction = "result";
        int supply = 0;
        int buy = 0;
        String[] statisticRecords = fruitStatistic.split(delimiter);
        for (String record : statisticRecords) {
            String[] splittedRecord = record.split(recordSeparator);
            if (splittedRecord[0].equals(supplyAction)) {
                supply += Integer.parseInt(splittedRecord[1]);
            } else if (splittedRecord[0].equals(buyAction)) {
                buy += Integer.parseInt(splittedRecord[1]);
            } else {
                throw new RuntimeException("Can't parse data: " + splittedRecord[0]);
            }
        }
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder(supplyAction).append(recordSeparator)
                .append(supply).append(System.lineSeparator())
                .append(buyAction).append(recordSeparator).append(buy)
                .append(System.lineSeparator())
                .append(resultAction).append(recordSeparator).append(result);
        return reportBuilder.toString();
    }

    private void writeFile(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
