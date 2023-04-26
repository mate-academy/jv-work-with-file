package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName));
                FileWriter fw = new FileWriter(toFileName)) {
            String value = br.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
                value = br.readLine();
            }
            int result = supply - buy;
            fw.write("supply," + supply + System.lineSeparator());
            fw.write("buy," + buy + System.lineSeparator());
            fw.write("result," + result + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
