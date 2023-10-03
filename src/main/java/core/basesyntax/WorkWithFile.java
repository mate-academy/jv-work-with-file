package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String content = readFile(fromFileName);
            String result = getResult(content);
            writeFile(toFileName, result);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing files: " + fromFileName, e);
        }
    }

    public String readFile(String fromFileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    public String getResult(String inputFromFile) {
        int buy = 0;
        int supply = 0;
        String[] lines = inputFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] words = line.split(COMMA_SEPARATOR);
            if (words.length == 2) {
                if (words[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(words[1]);
                } else if (words[0].equals(BUY)) {
                    buy += Integer.parseInt(words[1]);
                }
            }
        }
        int resultSum = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA_SEPARATOR)
                .append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA_SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA_SEPARATOR).append(resultSum);
        return stringBuilder.toString();
    }

    public void writeFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file: " + toFileName, e);
        }
    }
}
