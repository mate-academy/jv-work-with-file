package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFileName) {
        try {
            BufferedReader fromFileReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder dataFromFileBuilder = new StringBuilder();
            String value = fromFileReader.readLine();
            while (value != null) {
                dataFromFileBuilder.append(value).append(SEPARATOR);
                value = fromFileReader.readLine();
            }
            return dataFromFileBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
    }

    public String generateReport(String data) {
        String[] fromFileData = data.split(SEPARATOR);
        int buyCount = 0;
        int supplyCount = 0;
        for (int i = 0; i < fromFileData.length; i++) {
            if (fromFileData[i].equals(BUY)) {
                buyCount += Integer.parseInt(fromFileData[i + 1]);
            } else if (fromFileData[i].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(fromFileData[i + 1]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR)
                .append(supplyCount).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR)
                .append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supplyCount - buyCount);
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
