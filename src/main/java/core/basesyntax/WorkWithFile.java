package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotalAmount = 0;
        int buyTotalAmount = 0;
        for (String line: readFromFile(fromFileName).split("\\r?\\n")) {
            String[] row = line.split(",");
            if (row[0].equals("supply")) {
                supplyTotalAmount += Integer.valueOf(row[1]);
            } else {
                buyTotalAmount += Integer.valueOf(row[1]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("supply," + supplyTotalAmount + System.lineSeparator());
        result.append("buy," + buyTotalAmount + System.lineSeparator());
        result.append("result," + (supplyTotalAmount - buyTotalAmount));
        printToFile(toFileName, result.toString());
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                result.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file" + fromFileName, e);
        }
        return result.toString();
    }

    private void printToFile(String toFileName, String text) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file" + toFileName, e);
        }
    }
}
