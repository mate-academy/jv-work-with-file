package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String readingFromFile = readFromFile(fromFileName);
        String creatingReport = createReport(readingFromFile);
        writeToFile(creatingReport, toFileName);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while (reader.ready()) {
                stringBuilder.append(reader.readLine()).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String createReport(String value) {
        int buy = 0;
        int supply = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] parse = value.split(System.lineSeparator());
        for (int i = 0; i < parse.length; i++) {
            if (parse[i].contains(BUY)) {
                buy += Integer.parseInt(parse[i].split(COMMA)[1]);
            }
            if (parse[i].contains(SUPPLY)) {
                supply += Integer.parseInt(parse[i].split(COMMA)[1]);
            }
        }
        return stringBuilder
                    .append(SUPPLY + COMMA)
                    .append(supply)
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(COMMA)
                    .append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT)
                    .append(COMMA)
                    .append(supply - buy).toString();
    }

    public void writeToFile(String reportValue, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(reportValue);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
