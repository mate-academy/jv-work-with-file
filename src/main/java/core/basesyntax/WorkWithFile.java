package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readFromFile(fromFileName);
        reportData(statistic, toFileName);
    }

    public String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            while (bufferedReader.ready()) {
                String [] reader = bufferedReader.readLine().split(",");
                if (reader[0].equals(SUPPLY)) {
                    supply = supply + Integer.parseInt(reader[1]);
                } else if (reader[0].equals(BUY)) {
                    buy = buy + Integer.parseInt(reader[1]);
                }
            }
            return getReport(supply, buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
    }

    public String getReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator());
        builder.append("buy,").append(buy).append(System.lineSeparator());
        builder.append("result,").append(result);
        return builder.toString();
    }

    private void reportData(String statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file", e);
        }
    }
}
