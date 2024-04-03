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

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();

            while (value != null) {
                String[] valueArr = value.split(",");

                if (valueArr[0].equals("supply")) {
                    supply += Integer.parseInt(valueArr[1]);
                } else {
                    buy += Integer.parseInt(valueArr[1]);
                }

                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply" + "," + supply + "\n");
            writer.write("buy" + "," + buy + "\n");
            writer.write("result" + "," + result + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
