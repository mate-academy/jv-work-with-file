package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    private String[] readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("file could not be read");
        }
        String[] result = lines.toArray(new String[0]);
        return result;
    }

    private String[] createReport(String[] lines) {
        int buy = 0;
        int supply = 0;
        String[] temp;
        for (String line : lines) {
            temp = line.split(",");
            if (temp[0].equals("buy")) {
                buy = buy + Integer.parseInt(temp[1]);
            }
            if (temp[0].equals("supply")) {
                supply = supply + Integer.parseInt(temp[1]);
            }
        }
        String[] report = new String[3];
        report[0] = "supply," + supply;
        report[1] = "buy," + buy;
        report[2] = "result," + (supply - buy);
        return report;
    }

    private void writeReport(String[] lines, String toFilename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFilename))) {
            for (String line : lines) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("file could not be written to");
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName);
        String[] report = createReport(lines);
        writeReport(report, toFileName);
    }
}
