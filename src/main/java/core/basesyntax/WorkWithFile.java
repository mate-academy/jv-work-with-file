package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {

    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String rawData = readFromFile(fromFileName);
        String report = buildReport(rawData);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");

                for (String word : words) {
                    if (word.startsWith("s")) {
                        supply += Integer.parseInt(words[1]);
                    }
                    if (word.startsWith("b")) {
                        buy += Integer.parseInt(words[1]);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return fromFileName;
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            String rawData = new String();
            bufferedWriter.write(buildReport(rawData));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    public String buildReport(String rawData) {

        int result = (Math.abs(supply - buy));
        String[] arrayReport = new String[3];
        arrayReport[0] = "supply," + supply;
        arrayReport[1] = "buy," + buy;
        arrayReport[2] = "result," + result;
        StringBuilder report = new StringBuilder();

        for (String s : arrayReport) {
            report.append(s).append(System.lineSeparator());
        }
        return report.toString();
    }
}
