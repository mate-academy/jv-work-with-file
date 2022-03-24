package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    private final StringBuilder lineRead = new StringBuilder();
    private final StringBuilder report = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName).split(",");
        String report = createReport(getResult(data));
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineRead.append(line).append(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read all line", e);
        }
        return lineRead.toString();
    }

    private int[] getResult(String[] data) {
        int supply = 0;
        int buy = 0;
        for (int i = 0, j = 1; i < data.length; i += 2, j += 2) {
            if (data[i].equals(SUPPLY)) {
                supply += Integer.parseInt(data[j]);
            }
            if (data[i].equals(BUY)) {
                buy += Integer.parseInt(data[j]);
            }
        }
        return new int[] {supply, buy, supply - buy};
    }

    private String createReport(int[] result) {
        String nextLine = System.lineSeparator();
        report.append("supply,")
                .append(result[0])
                .append(nextLine)
                .append("buy,")
                .append(result[1])
                .append(nextLine)
                .append("result,")
                .append(result[2]);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write text in file", e);
        }
    }
}
