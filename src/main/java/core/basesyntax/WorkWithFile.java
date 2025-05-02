package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class WorkWithFile {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_DELIMITER = System.lineSeparator();
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = formReport(calculateSums(readFile(fromFileName)));
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        File sourceFile = new File(fromFileName);
        String[] data;
        try {
            String fileContent = Files.readString(sourceFile.toPath());
            data = fileContent.split(LINE_DELIMITER);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + sourceFile, e);
        }
        return data;
    }

    private int[] calculateSums(String[] data) {
        int supplySum = 0;
        int buySum = 0;
        for (String string : data) {
            String[] parts = string.split(COMMA_DELIMITER);
            String operationType = parts[0];
            int amount = Integer.parseInt(parts[1]);
            if (operationType.equals("supply")) {
                supplySum += amount;
            } else {
                buySum += amount;
            }
        }
        int result = supplySum - buySum;
        return new int[]{supplySum, buySum, result};
    }

    private String formReport(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply")
                .append(COMMA_DELIMITER)
                .append(data[SUPPLY_INDEX])
                .append(LINE_DELIMITER)
                .append("buy")
                .append(COMMA_DELIMITER)
                .append(data[BUY_INDEX])
                .append(LINE_DELIMITER)
                .append("result")
                .append(COMMA_DELIMITER)
                .append(data[RESULT_INDEX]);

        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String formedReport) {
        File targetFile = new File(toFileName);
        Path path = Paths.get(toFileName);
        try {
            Files.write(path, Collections.singleton(formedReport));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data in the file " + targetFile, e);
        }
    }
}
