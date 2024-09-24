package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String lineSeparator = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        int result = 0;

        //Read from file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splitArray = line.split(",");
                String operation = splitArray[0];
                int amount = Integer.parseInt(splitArray[1]);

                if (operation.equals("supply")) {
                    totalSupply += amount;
                } else if (operation.equals("buy")) {
                    totalBuy += amount;
                }
            }
            result = totalSupply - totalBuy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }

        String resultData = "supply," + totalSupply + lineSeparator
                + "buy," + totalBuy + lineSeparator
                + "result," + result + lineSeparator;

        //Write to File
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file");
        }
    }
}
