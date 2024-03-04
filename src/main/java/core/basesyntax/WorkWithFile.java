package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return data.toString();
    }

    private String createReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            if ("supply".equals(parts[0])) {
                supplyTotal += Integer.parseInt(parts[1]);
            } else if ("buy".equals(parts[0])) {
                buyTotal += Integer.parseInt(parts[1]);
            }
        }
        int result = supplyTotal - buyTotal;
        return "supply," + supplyTotal + System.lineSeparator()
               + "buy," + buyTotal + System.lineSeparator()
               + "result," + result;
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
