package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = new String[2];

        int supplyCount = 0;
        int buyCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                if (data[0].equals("supply")) {
                    supplyCount += Integer.parseInt(data[1]);
                }

                if (data[0].equals("buy")) {
                    buyCount += Integer.parseInt(data[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }

        try (BufferedWriter writter = new BufferedWriter(new FileWriter(toFileName))) {
            writter.write("supply," + supplyCount + "\n");
            writter.write("buy," + buyCount + "\n");
            writter.write("result," + (supplyCount - buyCount) + "\n");

        } catch (IOException e) {
            throw new RuntimeException("Can't write the file");
        }
    }
}
