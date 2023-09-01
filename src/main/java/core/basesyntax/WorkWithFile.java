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

    public static void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitLines;
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line + SPACE);
                line = bufferedReader.readLine();
            }
            splitLines = stringBuilder.toString().split(SIGN);
            for (int i = 0; i < splitLines.length; i++) {
                if (splitLines[i].equals("supply")) {
                    supply += Integer.parseInt(splitLines[i + 1]);
                    i++;
                } else {
                    buy += Integer.parseInt(splitLines[i + 1]);
                    i++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
        int result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            String report = "supply," + supply + SEPARATOR
                    + "buy," + buy + SEPARATOR
                    + "result," + result;
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file", e);
        }
    }
}
