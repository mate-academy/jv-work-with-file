package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(",", 2);
                if (parts.length != 2) {
                    throw new RuntimeException("Invalid CSV line: " + line);
                }

                String type = parts[0];
                int value = Integer.parseInt(parts[1]);

                switch (type) {
                    case "supply":
                        supplySum += value;
                        break;
                    case "buy":
                        buySum += value;
                        break;
                    default:
                        break;
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot open file.", e);
        }

        int result = supplySum - buySum;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + supplySum);
            bw.newLine();
            bw.write("buy," + buySum);
            bw.newLine();
            bw.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file: " + toFileName, e);
        }
    }
}
