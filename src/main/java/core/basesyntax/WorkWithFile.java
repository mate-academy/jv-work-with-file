package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readData(fromFileName);
        String[] calcData = calculatedData(data);
        writeData(calcData, toFileName);
    }

    private String[] readData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file ", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private String[] calculatedData(String[] calcDate) {
        int supplySum = 0;
        int buySum = 0;
        int quantity = 0;

        for (String line : calcDate) {
            String[] lineSeparator = line.split(CSV_SEPARATOR);
            quantity = Integer.parseInt(lineSeparator[1]);
            if (lineSeparator[0].equals("supply")) {
                supplySum += quantity;
            } else {
                buySum += quantity;
            }
        }
        int resulSum = supplySum - buySum;
        return new String[]{"supply," + supplySum, "buy," + buySum, "result," + resulSum};
    }

    private void writeData(String[] calcData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String data : calcData) {
                bufferedWriter.write(data);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file ", e);
        }
    }
}
