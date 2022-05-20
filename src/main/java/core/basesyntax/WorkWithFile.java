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

    public String readFile(String fileName) {
        try (BufferedReader bufferedReader =
                     Files.newBufferedReader(Paths.get(fileName))) {
            String value = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, e);
        }
    }

    public String evaluateData(String data) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] array = data.split(System.lineSeparator());
        System.out.println(data);
        for (String line : array) {
            if (line.toCharArray()[0] == 'b') {
                buy += Integer.parseInt(line.split(",")[1]);
            } else {
                supply += Integer.parseInt(line.split(",")[1]);
            }
        }
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return stringBuilder.toString();
    }

    public void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter =
                     Files.newBufferedWriter(Paths.get(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to file " + fileName, e);
        }
    }
}
