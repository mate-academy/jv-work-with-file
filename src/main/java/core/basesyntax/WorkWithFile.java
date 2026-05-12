package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = getStatisticsFromFile(fromFileName);
        writeStatisticsToFile(toFileName, report);
    }

    private String getStatisticsFromFile(String fromFileName) {
        StringBuilder report = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;

            while (line != null) {
                String[] words = line.split(",");
                if (words[0].equals(BUY)) {
                    buy += Integer.parseInt(words[1]);
                } else if (words[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(words[1]);
                }
                line = bufferedReader.readLine();
            }

            int result = supply - buy;

            report.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator());
            report.append(BUY).append(COMMA).append(buy).append(System.lineSeparator());
            report.append(RESULT).append(COMMA).append(result);

            return report.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private void writeStatisticsToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
