package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final String DELIMITER = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeStatistic(makeReport(readTheFile(fromFileName)), toFileName);
    }

    private List <String> readTheFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return lines;
    }

    private String makeReport (List <String> lines) {
        int buySum = 0;
        int supplySum = 0;
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            String[] split = line.split(DELIMITER);
            if (split[OPERATION_NAME_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(split[OPERATION_AMOUNT_INDEX]);
            }
            if (split[OPERATION_NAME_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(split[OPERATION_AMOUNT_INDEX]);
            }
        }
        int result = supplySum - buySum;
        builder.append(SUPPLY).append(DELIMITER).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(System.lineSeparator())
                .append("result").append(DELIMITER).append(result);
        return builder.toString();
    }

    private void writeStatistic(String statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,true))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
