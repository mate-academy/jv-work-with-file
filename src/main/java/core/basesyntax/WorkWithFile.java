package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileIn = new File(fromFileName);
        File fileOut = new File(toFileName);
        int sumBuy = 0;
        int sumSupply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn));
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut, true))) {

            String value = reader.readLine();

            while (value != null) {
                String [] parsedString = value.split(",");
                if (parsedString[0].equals("buy")) {
                    sumBuy += Integer.parseInt(parsedString[1]);
                } else {
                    sumSupply += Integer.parseInt(parsedString[1]);
                }
                value = reader.readLine();
            }

            writer.write("supply," + sumSupply + System.lineSeparator());
            writer.write("buy," + sumBuy + System.lineSeparator());
            writer.write("result," + (sumSupply - sumBuy));

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't wright file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

    }
}
