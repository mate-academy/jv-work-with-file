package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int allSupplyForDay = 0;
        int allBuyForDay = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = reader.readLine();
            while (readLine != null) {
                String[] splitLine = readLine.split(",");
                String type = splitLine[0];
                int count = Integer.parseInt(splitLine[1]);
                if (type.equals("supply")) {
                    allSupplyForDay += count;
                }
                if (type.equals("buy")) {
                    allBuyForDay += count;
                }

                readLine = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t found file - " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file - " + fromFileName, e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + allSupplyForDay + System.lineSeparator());
            writer.write("buy," + allBuyForDay + System.lineSeparator());
            writer.write("result," + (allSupplyForDay - allBuyForDay));
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }
}
