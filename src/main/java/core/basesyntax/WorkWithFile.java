package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int WORD_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] textLine = line.split(SEPARATOR);
                if (textLine[WORD_INDEX].equals("supply")) {
                    supply += Integer.parseInt(textLine[NUMBER_INDEX]);
                } else {
                    buy += Integer.parseInt(textLine[NUMBER_INDEX]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        writeReportToFile(toFileName, supply, buy);
    }

    private void writeReportToFile(String filename, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            StringBuilder report = new StringBuilder()
                    .append("supply,")
                    .append(supply)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(buy)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(supply - buy);
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
