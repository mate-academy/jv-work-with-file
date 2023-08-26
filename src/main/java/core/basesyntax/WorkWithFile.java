package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final int ARRAY_INDEX = 1;
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readDataFromFile(fromFileName);
        String report = createReport(data);
        writeReportToFile(toFileName, report);
    }

    private String readDataFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        int totalBuy = 0;
        int totalSupply = 0;
        StringBuilder result = new StringBuilder();
        String[] dataFile = dataFromFile.split(LINE_SEPARATOR);

        for (String datum : dataFile) {
            String[] data = datum.split(COMMA);

            if (datum.startsWith(BUY)) {
                totalBuy += Integer.parseInt(data[ARRAY_INDEX]);
            } else {
                totalSupply += Integer.parseInt(data[ARRAY_INDEX]);
            }
        }

        return result.append(SUPPLY).append(COMMA).append(totalSupply).append(LINE_SEPARATOR)
                     .append(BUY).append(COMMA).append(totalBuy).append(LINE_SEPARATOR)
                     .append(RESULT).append(COMMA).append(totalSupply - totalBuy).toString();
    }

    private void writeReportToFile(String toFile, String report) {
        try {
            Files.writeString(Path.of(toFile), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFile, e);
        }
    }
}
