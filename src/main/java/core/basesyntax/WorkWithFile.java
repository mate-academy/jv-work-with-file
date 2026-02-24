package core.basesyntax;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLines(fromFileName);
        int[] result = getResult(lines);
        String report = buildReport(result);
        writeReport(toFileName, report);
    }
    private List<String> readLines(String fromFileName) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return lines;
    }
    private int[] getResult(List<String> lines) {
        int[] result = new int[2];
        int supply = 0;
        int buy = 0;
        for (String el : lines) {
            String[] words = el.split(",");
            if (words[0].equals(SUPPLY)) {
                supply += Integer.parseInt(words[1]);
            } else if (words[0].equals(BUY)) {
                buy += Integer.parseInt(words[1]);
            }
        }
        result[0] = supply;
        result[1] = buy;
        return result;
    }
    private String buildReport(int[] result) {
        int supply = result[0];
        int buy = result[1];
        int total = supply - buy;

        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + total;
    }
    private void writeReport(String toFileName, String report) {
        try {
            Files.writeString(
                    Path.of(toFileName),
                    report,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
