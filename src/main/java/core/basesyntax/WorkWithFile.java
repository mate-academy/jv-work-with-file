package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (line.startsWith("supply")) {
                    supplyAmount += Integer.parseInt(parts[1]);
                } else {
                    buyAmount += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        writeToFile(toFileName, supplyAmount, buyAmount);
    }

    public String createReport(int supplyAmount, int buyAmount) {
        int resultAmount = supplyAmount - buyAmount;
        return "supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator()
                + "result," + resultAmount;
    }

    public void writeToFile(String toFileName, int supplyAmount, int buyAmount) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReport(supplyAmount, buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
