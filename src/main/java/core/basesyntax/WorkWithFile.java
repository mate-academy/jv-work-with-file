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
    private static final String COMMA_SEPARATOR = ",";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(
                composeReport(
                readFromFile(fromFileName)), toFileName);
    }

    public void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }

    public String readFromFile(String fromFileName) {
        String result;
        try {
            result = Files.readString(Path.of(fromFileName))
                    .replaceAll("\\r?\\n|\\r", COMMA_SEPARATOR);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return result;
    }

    public String composeReport(String data) {
        String [] tmp = data.split(COMMA_SEPARATOR);
        int [] report = new int[2];

        for (int i = 0; i < tmp.length; i++) {
            switch (tmp[i]) {
                case ("supply"):
                    report[SUPPLY_INDEX] += Integer.parseInt(tmp[i + 1]);
                    break;
                case ("buy"):
                    report[BUY_INDEX] += Integer.parseInt(tmp[i + 1]);
                    break;
                default:
            }
        }

        return SUPPLY + COMMA_SEPARATOR + report[SUPPLY_INDEX] + System.lineSeparator()
                + BUY + COMMA_SEPARATOR + report[BUY_INDEX] + System.lineSeparator()
                + RESULT + COMMA_SEPARATOR + (report[SUPPLY_INDEX] - report[BUY_INDEX]);
    }
}
