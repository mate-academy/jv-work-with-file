package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String [] dataLine = line.split(",");
                if (dataLine[0].equals("supply")) {
                    sumSupply += Integer.parseInt(dataLine[1]);
                }
                if (dataLine[0].equals("buy")) {
                    sumBuy += Integer.parseInt(dataLine[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + sumSupply + System.lineSeparator()
                                + "buy," + sumBuy + System.lineSeparator()
                                + "result," + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: ", e);
        }
    }
}
