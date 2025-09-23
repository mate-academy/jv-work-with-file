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

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = br.readLine()) != null) {
                String[] values = value.split(",");
                if (values[0].equals("supply")) {
                    supply += Integer.parseInt(values[1]);
                } else if (values[0].equals("buy")) {
                    buy += Integer.parseInt(values[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + supply);
            bw.newLine();
            bw.write("buy," + buy);
            bw.newLine();
            bw.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
