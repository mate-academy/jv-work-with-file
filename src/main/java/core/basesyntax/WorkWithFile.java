package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        Map<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());

        for (String line : lines) {
            String[] splitLine = line.split(",");
            map.merge(splitLine[0], Integer.parseInt(splitLine[1]), Integer::sum);
        }

        writeToFile(map, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException ex) {
            throw new RuntimeException("Can`t read data from the file "
                    + fromFileName, ex);
        }
    }

    private void writeToFile(Map<String, Integer> map, String toFileName) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName),
                StandardCharsets.UTF_8)) {
            map.forEach((key, value) -> {
                try {
                    bufferedWriter.write(key + "," + value);
                    bufferedWriter.write(System.lineSeparator());
                } catch (IOException ex) {
                    throw new RuntimeException("Can`t write entry with key = " + key
                            + " and value = " + value, ex);
                }
            });

            bufferedWriter.write("result," + getDifferenceBetweenSupplierAndBuy(map.get("supply"),
                    map.get("buy")));
        } catch (IOException ex) {
            throw new RuntimeException("Can`t write data to the file "
                    + toFileName, ex);
        }
    }

    private Integer getDifferenceBetweenSupplierAndBuy(Integer supply, Integer buy) {
        return supply - buy;
    }
}
