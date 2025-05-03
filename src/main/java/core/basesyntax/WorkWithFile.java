package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String CSV_SEPARATOR = ",";
    private static final int KEY_IN_ARRAY = 0;
    private static final int VALUE_IN_ARRAY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String rawData = readDataFromFile(fromFileName);
        String reportData = generateReport(rawData);
        writeDataToFile(reportData, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        try {
            return Files.readString(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName);
        }
    }

    private String generateReport(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer supply = 0;
        Integer buy = 0;

        for (String fileLine : data.split(System.lineSeparator())) {
            String[] fileLineSplitted = fileLine.split(CSV_SEPARATOR);
            if (fileLineSplitted[KEY_IN_ARRAY].equals(SUPPLY)) {
                supply += Integer.parseInt(fileLineSplitted[VALUE_IN_ARRAY]);
            } else if (fileLineSplitted[KEY_IN_ARRAY].equals(BUY)) {
                buy += Integer.parseInt(fileLineSplitted[VALUE_IN_ARRAY]);
            }
        }

        return stringBuilder.append(SUPPLY + CSV_SEPARATOR)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY + CSV_SEPARATOR)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT + CSV_SEPARATOR)
                .append(supply - buy)
                .toString();

    }

    private void writeDataToFile(String data, String toFileName) {
        try {
            Files.write(Path.of(toFileName), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName);
        }
    }
}
