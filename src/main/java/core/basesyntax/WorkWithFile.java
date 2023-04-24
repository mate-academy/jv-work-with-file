package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(",");
                int amount = Integer.parseInt(arr[1]);
                String op = arr[0];
                if (op.equals("supply")) {
                    supplyTotal += amount;
                } else if (op.equals("buy")) {
                    buyTotal += amount;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file.", e);
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write("supply," + supplyTotal + System.lineSeparator());
            bufferedWriter.write("buy," + buyTotal + System.lineSeparator());
            bufferedWriter.write("result," + (supplyTotal - buyTotal) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file.", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close BufferWriter.", e);
                }
            }
        }
    }
}
