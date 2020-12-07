package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File read = new File(fromFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(read))) {
            StringBuilder temporary = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                temporary.append(value).append(" ");
                value = reader.readLine();
            }
            String[] lines = temporary.toString().split(" ");
            int supply = 0;
            int buy = 0;
            for (String line : lines) {
                String[] operation = line.split(DELIMITER);
                if (operation[ZERO].equals(SUPPLY)) {
                    supply += Integer.parseInt(operation[ONE]);
                } else {
                    buy += Integer.parseInt(operation[ONE]);
                }
            }
            createReport(supply, buy, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private void createReport(int supply, int buy, String toFileName) {
        File report = new File(toFileName);
        StringBuilder results = new StringBuilder();
        results.append(SUPPLY).append(DELIMITER).append(supply).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buy).append(System.lineSeparator())
                .append("result").append(DELIMITER).append(supply - buy);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
            bufferedWriter.write(results.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
