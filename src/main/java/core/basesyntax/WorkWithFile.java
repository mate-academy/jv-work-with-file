package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String DELIMITER = "\\W+";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> strings = readFile(new File(fromFileName));
        String createdReport = createReport(strings);
        writeToFile(createdReport, String.valueOf(new File(toFileName)));
    }

    private List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + file, e);
        }
    }

    private String createReport(List<String> records) {
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String data : records) {
            String[] splitData = data.split(DELIMITER);
            if (splitData[SUPPLY_INDEX].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(splitData[VALUE_INDEX]);
            } else {
                buyCounter += Integer.parseInt(splitData[VALUE_INDEX]);
            }
        }

        return new StringBuilder()
                .append("supply,")
                .append(supplyCounter)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyCounter)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyCounter - buyCounter)
                .toString();
    }

    private void writeToFile(String createdReport, String fileToWrite) {
        try {
            Files.write(new File(fileToWrite).toPath(), createdReport.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't create " + fileToWrite, e);
        }
    }
}
