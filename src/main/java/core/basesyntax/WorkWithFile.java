package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFile(fromFileName);
        writeFile(result, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder report = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.split(COMMA)[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(value.split(COMMA)[1]);
                } else if (value.split(COMMA)[0].equals(BUY)) {
                    buy += Integer.parseInt(value.split(COMMA)[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }
        return getReport(report.toString(), supply, buy);
    }

    private String getReport(String dataFromFile, int supply, int buy) {
        StringBuilder result = new StringBuilder(dataFromFile);
        return result.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy).toString();
    }

    private void writeFile(String writeText, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(writeText);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
