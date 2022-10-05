package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT = "result,";

    private String[] readData(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                content.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString().split(System.lineSeparator());
    }

    private String reportReady(String[] readData) {
        StringBuilder result = new StringBuilder();
        int supply = 0;
        int buy = 0;
        for (String s : readData) {
            String[] sup = s.split(",");
            if (s.contains(SUPPLY)) {
                supply = supply + Integer.parseInt(sup[1]);
            }
            if (s.contains(BUY)) {
                buy = buy + Integer.parseInt(sup[1]);
            }
        }
        return result.append(SUPPLY).append(supply).append(System.lineSeparator())
                .append(BUY).append(buy).append(System.lineSeparator())
                .append(RESULT).append(supply - buy).append(System.lineSeparator()).toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportReady(readData(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
