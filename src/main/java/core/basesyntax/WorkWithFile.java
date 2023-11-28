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

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the data from Filename", e);
        }
        int result = supply - buy;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to FileName", e);
        }

    }
}












