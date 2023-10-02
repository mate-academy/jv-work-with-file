package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromfile = readData(fromFileName);
        String report = createReport(dataFromfile);
        writeToFile(report, toFileName);
    }

    private String[] readData(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(Files.readString(Path.of(fromFileName))).append(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] dataFromfile) {
        int supplyCounter = 0;
        int buyCounter = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dataFromfile.length; i++) {
            String[] oneLine = dataFromfile[i].split(COMMA);
            if (oneLine[INDEX_OF_OPERATION].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(oneLine[INDEX_OF_AMOUNT]);
            }
            if (oneLine[INDEX_OF_OPERATION].equals(BUY)) {
                buyCounter += Integer.parseInt(oneLine[INDEX_OF_AMOUNT]);
            }
        }
        return builder.append(SUPPLY).append(COMMA)
                .append(supplyCounter).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyCounter).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplyCounter - buyCounter).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
