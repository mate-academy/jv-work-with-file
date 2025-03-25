package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String fromFileLine;
            while ((fromFileLine = reader.readLine()) != null) {
                String[] fromFileSplit = fromFileLine.split(",");
                if (fromFileSplit[0].equals("buy")) {
                    buySum += Integer.parseInt(fromFileSplit[1]);
                } else {
                    supplySum += Integer.parseInt(fromFileSplit[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file: " + toFileName, e);
        }

        int result = supplySum - buySum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            System.out.println("supply," + supplySum + System.lineSeparator()
                    + "buy," + buySum + System.lineSeparator() + "result," + result);
            writer.write("supply," + supplySum + System.lineSeparator()
                    + "buy," + buySum + System.lineSeparator() + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
