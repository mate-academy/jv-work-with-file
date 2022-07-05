package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeReportToFile(toFileName, readDataFromFile(fromFileName));
    }

    private String[] readDataFromFile(String fromFileName) {
        try {
            File fromFile = new File(fromFileName);
            return Files.readAllLines(fromFile.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fromFileName, e);
        }
    }

    private void writeReportToFile(String toFileName, String[] strings) {
        try {
            File toFile = new File(toFileName);
            toFile.createNewFile();
            String[] resultString = resultString(strings);
            for (String string: resultString) {
                Files.writeString(toFile.toPath(),string, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + toFileName, e);
        }
    }

    private String[] resultString(String[] strings) {
        int buyTotal = 0;
        int supplyTotal = 0;
        for (String string: strings) {
            if (string.split(COMMA)[0].equals(BUY)) {
                buyTotal += Integer.parseInt(string.split(COMMA)[1]);
            }
            if (string.split(COMMA)[0].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(string.split(COMMA)[1]);
            }
        }
        int result = supplyTotal - buyTotal;
        return new String[] {SUPPLY + COMMA + supplyTotal + System.lineSeparator(),
                BUY + COMMA + buyTotal + System.lineSeparator(),
                RESULT + COMMA + result};
    }
}
