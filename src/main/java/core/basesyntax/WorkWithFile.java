package core.basesyntax;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fromFileName));
            String value = br.readLine();
            while (value != null) {
                String[] values = value.split(",");
                if (values[0].equals("supply")) {
                    supply += Integer.parseInt(values[1]);
                } else if (values[0].equals("buy")) {
                    buy += Integer.parseInt(values[1]);
                }
                value = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            File file = new File(toFileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName));
            bw.write("supply," + supply);
            bw.newLine();
            bw.write("buy," + buy);
            bw.newLine();
            bw.write("result," + (supply - buy));
            bw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
