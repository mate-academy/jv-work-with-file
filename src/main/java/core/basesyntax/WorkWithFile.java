package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int WORD_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                dataFromFile
                        .append(line)
                        .append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return dataFromFile.toString();
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] dataLines = dataFromFile.split(System.lineSeparator());

        for (int i = 0; i < dataLines.length; i++) {
            String[] separateWords = dataLines[i].split(SEPARATOR);
            if (separateWords[WORD_INDEX].equals("supply")) {
                supply += Integer.parseInt(separateWords[NUMBER_INDEX]);
            } else {
                buy += Integer.parseInt(separateWords[NUMBER_INDEX]);
            }
        }

        StringBuilder report = new StringBuilder()
                .append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);

        return report.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
