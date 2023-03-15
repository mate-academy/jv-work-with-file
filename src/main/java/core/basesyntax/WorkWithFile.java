package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int VALUE = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeFile(toFileName, report);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file "
                    + file.getName(), e);
        }
    }

    private String generateReport(List<String> dataFromFile) {
        int supplyCount = 0;
        int buyCount = 0;

        for (String line : dataFromFile) {
            String[] data = line.split(DELIMITER);
            if (data[OPERATION].equals(OPERATION_SUPPLY)) {
                supplyCount += Integer.parseInt(data[VALUE]);
            }
            if (data[OPERATION].equals(OPERATION_BUY)) {
                buyCount += Integer.parseInt(data[VALUE]);
            }
        }

        return String.format("%s,%d\n%s,%d\n%s,%d\n",
                OPERATION_SUPPLY, supplyCount,
                OPERATION_BUY, buyCount,
                RESULT, supplyCount - buyCount);
    }

    private void writeFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to " + file.getName(), e);
        }
    }
}
