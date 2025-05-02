package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String result = this.readFile(fromFileName);
        String statistic = this.createReport(result);
        this.writeStatistic(statistic, toFileName);
    }

    public String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value + ",");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String createReport(String readinggg) {
        int supplySum = 0;
        int buySum = 0;
        String[] elements = readinggg.split(",");
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals("supply")) {
                supplySum = supplySum + Integer.parseInt(elements[i + 1]);
            } else if (elements[i].equals("buy")) {
                buySum = buySum + Integer.parseInt(elements[i + 1]);
            }
        }
        return new String("supply," + supplySum + '\n'
                + "buy," + buySum + '\n'
                + "result," + (supplySum - buySum));
    }

    public void writeStatistic(String statistic, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
