package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int WORD_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String statisticsResult = generateStatisticsFromFile(fromFileName);
        writeToFile(toFileName, statisticsResult);
    }

    private String generateStatisticsFromFile(String fromFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] data = line.split(COMMA);
                if (data[WORD_INDEX].equals("buy")) {
                    buy += Integer.parseInt(data[VALUE_INDEX]);
                } else {
                    supply += Integer.parseInt(data[VALUE_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file.");
        }
        return generateReport(buy, supply);
    }

    private void writeToFile(String toFileName, String text) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write text to file.");
        }
    }

    private String generateReport(int buy, int supply) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }
}
