package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + getSupplyAmount(fromFileName)
                    + System.lineSeparator());
            bufferedWriter.write("buy," + getBuyAmount(fromFileName)
                    + System.lineSeparator());
            bufferedWriter.write("result,"
                    + (getSupplyAmount(fromFileName) - getBuyAmount(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e);
        }

    }

    private int getSupplyAmount(String csvFile) throws FileNotFoundException {
        int supplyAmount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String value = reader.readLine();

            while (value != null) {
                String[] splitValue = value.split(",");
                if (splitValue[0].equals("supply")) {
                    supplyAmount += Integer.parseInt(splitValue[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return supplyAmount;
    }

    private int getBuyAmount(String csvFile) throws FileNotFoundException {
        int buyAmount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String value = reader.readLine();

            while (value != null) {
                String[] splitValue = value.split(",");
                if (splitValue[0].equals("buy")) {
                    buyAmount += Integer.parseInt(splitValue[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buyAmount;
    }
}
