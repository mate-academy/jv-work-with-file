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
                if (operation[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(operation[1]);
                } else {
                    buy += Integer.parseInt(operation[1]);
                }
            }
            createReport(supply, buy, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void createReport(int supply, int buy, String toFileName) {
        File report = new File(toFileName);
        StringBuilder results = new StringBuilder();
        results.append(SUPPLY).append(",").append(supply).append("\n")
                .append(BUY).append(",").append(buy).append("\n")
                .append("result,").append(supply - buy);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
            bufferedWriter.write(results.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
