package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts [0];
                int value = Integer.parseInt(parts[1]);
                if (operation.equals("supply")) {
                    supplySum += value;
                } else if (operation.equals("buy")) {
                    buySum += value;
                }
            }
            int result = supplySum - buySum;
            FileWriter fileWriter = new FileWriter(toFileName);
            fileWriter.write("supply," + supplySum + System.lineSeparator());
            fileWriter.write("buy," + buySum + System.lineSeparator());
            fileWriter.write("result," + result + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
