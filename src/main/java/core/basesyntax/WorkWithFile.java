package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] values = readeFromFile(fromFileName).split(",");
        writerToFile(toFileName,countStatistic(values));
    }

    private String countStatistic(String[] values) {
        int supplySum = 0;
        int buySum = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1].equals("supply")) {
                supplySum = Integer.parseInt(values[i].trim()) + supplySum;
            } else if (values[i - 1].equals("buy")) {
                buySum = Integer.parseInt(values[i].trim()) + buySum;
            }
        }
        int result = supplySum - buySum;
        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator() + "result," + result;
    }

    private String readeFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value);
                stringBuilder.append(",");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't not reade data from file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private void writerToFile(String fileName, String statistic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't not write data to file" + fileName, e);
        }
    }
}
