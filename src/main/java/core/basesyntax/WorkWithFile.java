package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkWithFile {
    private LinkedHashMap<String, Integer> report = new LinkedHashMap<>();

    public void getStatistic(String fromFileName, String toFileName) {
        readStatisticFromFile(fromFileName);

        writeStatisticToFile(toFileName);
    }

    private void readStatisticFromFile(String fileName) {
        initializeStatisticHashMap();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                writeStatisticToHashMap(value);
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file " + fileName, e);
        }

        calculateResultOfStatistic();
    }

    private void initializeStatisticHashMap() {
        report.put("supply", 0);
        report.put("buy", 0);
        report.put("result", 0);
    }

    private void writeStatisticToHashMap(String line) {
        String[] lineArray = line.split(",");

        report.computeIfPresent(lineArray[0],
                (key, value) -> value + Integer.parseInt(lineArray[1]));
    }

    private void calculateResultOfStatistic() {
        int supply = report.get("supply");
        int buy = report.get("buy");

        report.computeIfPresent("result",
                (key, value) -> supply - buy);
    }

    private void writeStatisticToFile(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : report.entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}
