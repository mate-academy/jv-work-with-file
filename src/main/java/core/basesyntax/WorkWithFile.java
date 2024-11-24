package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                String operation = split[0];
                int number = Integer.parseInt(split[1]);
                if (operation.equals("supply")) {
                    supply += number;
                }
                if (operation.equals("buy")) {
                    buy += number;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write out data from a file",e);
        }
        result = supply - buy;
        String report = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file",e);
        }
        

    }
}
