package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)));
            String value = reader.readLine();
            while (value != null) {
                String[] values = value.split(",");
                switch (values[0]) {
                    case "supply":
                        supply += Integer.parseInt(values[1]);
                        break;
                    case "buy":
                        buy += Integer.parseInt(values[1]);
                        break;
                    default:
                        break;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        // not writing to file if it already exists
        File file = new File(toFileName);
        if (file.exists()) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            String separator = System.lineSeparator();
            writer.write("supply," + supply + separator);
            writer.write("buy," + buy + separator);
            writer.write("result," + (supply - buy) + separator);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
