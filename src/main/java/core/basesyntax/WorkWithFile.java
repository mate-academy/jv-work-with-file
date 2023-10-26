package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int FIELD_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    public String readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            return Files.readString(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist " + fromFileName);
        }
    }

    public String createReport(String dataFromFile) {
        int supplySum = 0;
        int buySum = 0;
        for (String raw : dataFromFile.split(System.lineSeparator())) {
            String[] temp = raw.split(COMMA);
            if (temp[FIELD_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(temp[VALUE_INDEX]);
            } else {
                buySum += Integer.parseInt(temp[VALUE_INDEX]);
            }
        }
        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplySum - buySum)
                .toString();
    }

    public void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(toFile)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist " + toFileName);
        }
    }
}
