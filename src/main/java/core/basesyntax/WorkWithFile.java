package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> content = read(fromFileName);
        String res = calculateResult(content);
        write(toFileName, res);
    }

    private List<String> read(String fileName) {
        File file = new File(fileName);
        //List<String> content = Files.readAllLines(Path.of(fileName));
        List<String> content = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
        return content;
    }

    private void write(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter bufferedEWriter = new BufferedWriter(new FileWriter(file))) {
            //Files.writeString(Path.of(fileName), data);
            bufferedEWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }

    private String calculateResult(List<String> content) {
        Map<String, Integer> data = new LinkedHashMap<>();
        for (String line: content) {
            String[] valueAndKey = line.split(",");
            String key = valueAndKey[0];
            int value = Integer.parseInt(valueAndKey[1]);
            if (key.equals("supply") || key.equals("buy")) {
                data.compute(key, (k, oldValue) -> (oldValue == null)
                        ? value : oldValue + value);
            } else {
                data.put(key, value);
            }
        }
        int difference = data.getOrDefault("supply", 0) - data.getOrDefault("buy", 0);
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(data.get("supply")).append(System.lineSeparator());
        report.append("buy,").append(data.get("buy")).append(System.lineSeparator());
        report.append("result,").append(difference);
        return report.toString();
    }
}
