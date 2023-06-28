package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String[] REPORT_CONTENT = {"supply", "buy", "result"};
    private static final String COMMA_SEPARATOR = ",";
    private static final String REGEX = "\\r?\\n|\\r";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(composeReport(readFromFile(fromFileName)), toFileName);
    }

    public void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }

    public int[] readFromFile(String fromFileName) {
        int[] result = new int[2];
        String[] tmp;
        try {
            tmp = Files.readString(Path.of(fromFileName))
                    .replaceAll(REGEX, COMMA_SEPARATOR).split(COMMA_SEPARATOR);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        for (int i = 0; i < tmp.length; i++) {
            switch (tmp[i]) {
                case ("supply"):
                    result[SUPPLY_INDEX]
                            = result[SUPPLY_INDEX] + Integer.parseInt(tmp[i + 1]);
                    break;
                case ("buy"):
                    result[BUY_INDEX]
                            = result[BUY_INDEX] + Integer.parseInt(tmp[i + 1]);
                    break;
                default:
            }
        }
        return result;
    }

    public String composeReport(int[] data) {
        return REPORT_CONTENT[0] + COMMA_SEPARATOR + data[SUPPLY_INDEX] + System.lineSeparator()
                + REPORT_CONTENT[1] + COMMA_SEPARATOR + data[BUY_INDEX] + System.lineSeparator()
                + REPORT_CONTENT[2] + COMMA_SEPARATOR + (data[SUPPLY_INDEX] - data[BUY_INDEX]);
    }
}
