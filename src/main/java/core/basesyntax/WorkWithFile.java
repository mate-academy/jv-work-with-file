package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
        int supplySum = 0;
        int buySum = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splited = line.split(",");
            String operation = splited[0];
            int amount = Integer.parseInt(splited[1]);
            if (operation.equalsIgnoreCase("supply")) {
                supplySum += amount;
            } else if (operation.equalsIgnoreCase("buy")) {
                buySum += amount;
            }
        }
        reader.close();
        int result = supplySum - buySum;
        writer.write("supply," + supplySum + "\n");
        writer.write("buy," + buySum + "\n");
        writer.write("result," + result + "\n");
        writer.close();
    }
}
