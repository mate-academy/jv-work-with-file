package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final char COMMA = ',';

    protected void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String result = calculateStatistics(data);
        writeFile(toFileName, result);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file " + fromFileName, e);
        }
        return content.toString().split(System.lineSeparator());
    }

    private String calculateStatistics(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String line : data) {
            String[] lineContent = line.split(String.valueOf(COMMA));
            if (lineContent[0].equals(SUPPLY)) {
                supply += Integer.parseInt(lineContent[1]);
            } else if (lineContent[0].equals(BUY)) {
                buy += Integer.parseInt(lineContent[1]);
            }
        }
        int result = supply - buy;

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator());
        resultBuilder.append(BUY).append(COMMA).append(buy).append(System.lineSeparator());
        resultBuilder.append(RESULT).append(COMMA).append(result);

        return resultBuilder.toString();
    }

    private void writeFile(String toFileName, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
