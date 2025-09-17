package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        Map<String, Integer> reports = new HashMap<String, Integer>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();

            while (value != null) {
                String[] values = value.split(",");
                String product = values[0];

                int count = Integer.parseInt(values[1]);

                reports.merge(product, count, Integer::sum);

                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }

        File result = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(result))) {
            writer.write("supply," + reports.get("supply") + "\n");
            writer.write("buy," + reports.get("buy") + "\n");

            int comparison = reports.get("supply") - reports.get("buy");

            writer.write("result," + comparison);

        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
