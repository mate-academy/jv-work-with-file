package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (type.equals("supply")) {
                    supply = supply + amount;
                }
                if (type.equals("buy")) {
                    buy = buy + amount;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
        int result = supply - buy;
        String[] report = new String[3];
        report[0] = "supply," + supply;
        report[1] = "buy," + buy;
        report[2] = "result," + result;
        File myFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(myFile))) {
            for (String pure : report) {
                writer.write(pure);
                writer.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
