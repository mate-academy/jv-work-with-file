package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private int supplyTotal = 0;
    private int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readData(fromFileName);
        String report = createReport(readData);
        writeData(report, toFileName);
    }

    private String readData(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read file ", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        String[] lines = data.split(System.lineSeparator());
        for (String line: lines) {
            String[] lineParts = line.split(",");
            if (lineParts[0].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(lineParts[1]);
            } else if (lineParts[0].equals(BUY)) {
                buyTotal += Integer.parseInt(lineParts[1]);
            }
        }

        return "supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator()
                + "result," + (supplyTotal - buyTotal);
    }

    private void writeData(String data, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file", e);
        }
    }
}
