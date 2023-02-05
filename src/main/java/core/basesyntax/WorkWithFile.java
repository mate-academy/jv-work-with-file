package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String [] data = line.split(",");
                switch (data[0]) {
                    case "supply":
                        supplySum += Integer.parseInt(data[1]);
                        break;
                    case "buy":
                        buySum += Integer.parseInt(data[1]);
                        break;
                    default:
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        try (BufferedWriter reader = new BufferedWriter(new FileWriter(toFileName))) {
            reader.write("supply," + supplySum + System.lineSeparator());
            reader.write("buy," + buySum + System.lineSeparator());
            reader.write("result," + (supplySum - buySum) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
