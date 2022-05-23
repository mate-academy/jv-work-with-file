package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String rawData = readFile(fromFileName);
        String evaluatedData = evaluateData(rawData);
        writeFile(toFileName, evaluatedData);
    }

    private String readFile(String fileName) {
        try (BufferedReader bufferedReader =
                     Files.newBufferedReader(Paths.get(fileName))) {
            String value = bufferedReader.readLine();
            final StringBuilder stringBuilder = new StringBuilder();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, e);
        }
    }

    private String evaluateData(String data) {
        int supply = 0;
        int buy = 0;
        final String wordBuy = "buy";
        final String wordSupply = "supply";
        final String wordResult = "result";
        final String comma = ",";
        final String separator = System.lineSeparator();
        final StringBuilder stringBuilder = new StringBuilder();
        final String[] array = data.split(separator);
        for (String line : array) {
            final String[] words = line.split(comma);
            if (words[0].equals(wordBuy)) {
                buy += Integer.parseInt(words[1]);
            } else if (words[0].equals(wordSupply)) {
                supply += Integer.parseInt(words[1]);
            }
        }
        stringBuilder.append(wordSupply).append(comma).append(supply).append(separator)
                .append(wordBuy).append(comma).append(buy).append(separator)
                .append(wordResult).append(comma).append(supply - buy);
        return stringBuilder.toString();
    }

    private void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter =
                     Files.newBufferedWriter(Paths.get(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to file " + fileName, e);
        }
    }
}
