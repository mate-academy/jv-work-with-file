package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReportFromData(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String filepath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filepath));
            return lines.toArray(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException("can't read a file", e);
        }
    }

    private String createReportFromData(String[] getData) {
        int supply = 0;
        int buy = 0;
        for (String data : getData) {
            String[] splitData = data.split(",");
            if (splitData[0].equals(SUPPLY)) {
                supply += Integer.parseInt(splitData[1]);
            }
            if (splitData[0].equals(BUY)) {
                buy += Integer.parseInt(splitData[1]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        return builder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can't write a file", e);
        }

    }
}






