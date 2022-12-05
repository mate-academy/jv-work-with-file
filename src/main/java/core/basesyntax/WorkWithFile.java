package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                if (split[0].equals("supply")) {
                    supply += Integer.parseInt(split[1]);
                } else {
                    buy += Integer.parseInt(split[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }

        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            String supplyString = "supply" + "," + supply;
            String buyString = "buy" + "," + buy;
            String resultString = "result" + "," + (supply - buy);
            writer.write(supplyString);
            writer.newLine();
            writer.write(buyString);
            writer.newLine();
            writer.write(resultString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
