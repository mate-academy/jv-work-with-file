package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int WORD_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String FIRST_CHARACTER = "s";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile((createReport(readFromFile(fromFileName))), toFileName);
    }

    private String readFromFile(String fromFileName) {
        String data;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            data = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return data;
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] lines = data.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            String[] splittedLine = lines[i].split(",");
            int amount = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            if (splittedLine[WORD_INDEX].startsWith(FIRST_CHARACTER)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        StringBuilder stringReport = new StringBuilder();
        stringReport.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        String report = stringReport.toString();
        return report;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
