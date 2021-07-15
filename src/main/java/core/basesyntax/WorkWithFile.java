package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String VALUE_SUPPLY = "supply";
    private static final String VALUE_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readData(fromFileName);
        writeReport(toFileName, data);
    }

    private int[] readData(String fromFileName) {
        File file = new File(fromFileName);
        int sumSupply = 0;
        int sumBuy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            String value = reader.readLine();
            while (value != null) {
                String[] splitValue = value.split(",");
                if (VALUE_SUPPLY.equals(splitValue[0])) {
                    sumSupply += Integer.parseInt(splitValue[1]);
                } else if (VALUE_BUY.equals(splitValue[0])) {
                    sumBuy += Integer.parseInt(splitValue[1]);
                } else {
                    throw new RuntimeException("There is wrong data in " + fromFileName);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return new int[] {sumSupply, sumBuy};
    }

    private void writeReport(String toFileName, int[] data) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't write a new file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));) {
            bufferedWriter.write("supply," + data[0]);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + data[1]);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (data[0] - data[1]));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
