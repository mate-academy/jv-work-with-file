package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SIGN = "\\W+";
    private static final String SPACE = " ";
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] splitLines = readLines(fromFileName);
        String calculatedReport = calculateArray(splitLines);
        writeToFile(toFileName, calculatedReport);
    }

    private String[] readLines(String filePath) {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitLines;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line + SPACE);
                line = bufferedReader.readLine();
            }
            splitLines = stringBuilder.toString().split(SIGN);
            return splitLines;
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
    }

    private String calculateArray(String[] splitLines) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < splitLines.length; i++) {
            if (splitLines[i].equals("supply")) {
                supply += Integer.parseInt(splitLines[i + 1]);
                i++;
            } else {
                buy += Integer.parseInt(splitLines[i + 1]);
                i++;
            }
        }
        int result = supply - buy;
        String report = "supply," + supply + SEPARATOR
                + "buy," + buy + SEPARATOR
                + "result," + result;
        return report;
    }

    private void writeToFile(String filePath, String message) {
        File file = new File(filePath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }
}
