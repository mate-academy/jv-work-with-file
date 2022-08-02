package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String statistic = createStatistic(data);
        writeToFile(statistic, toFileName);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder dataBuilder = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String lineValue = bufferedReader.readLine();
            while (lineValue != null) {
                dataBuilder.append(lineValue).append(",");
                lineValue = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file", e);
        } catch (IOException ex) {
            throw new RuntimeException("Can't read the file", ex);
        }
        return dataBuilder.toString().split(",");
    }

    private String createStatistic(String[] data) {
        int supplyValue = 0;
        int buyValue = 0;
        StringBuilder statisticBuilder = new StringBuilder("supply");
        for (int i = 0; i < data.length; i += 2) {
            if (data[i].equals("supply")) {
                supplyValue += Integer.parseInt(data[i + 1]);
            } else {
                buyValue += Integer.parseInt(data[i + 1]);
            }
        }
        int result = supplyValue - buyValue;
        statisticBuilder.append(",").append(supplyValue)
                .append(System.lineSeparator()).append("buy").append(",")
                .append(buyValue).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return statisticBuilder.toString();
    }

    public void writeToFile(String statistic, String toFileName) {
        Path filePath = Paths.get(toFileName);
        try {
            Files.createFile(filePath);
            Files.write(filePath, statistic.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }
    }
}
