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
        try (BufferedReader file = new BufferedReader(new FileReader(fromFileName))) {
            String value = null;

            while ((value = file.readLine()) != null) {
                String[] arraysLines = value.split(",");

                if (arraysLines[0].equals("supply")) {
                    supply += Integer.parseInt(arraysLines[1]);
                } else {
                    buy += Integer.parseInt(arraysLines[1]);
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();

            writer.write("buy," + buy);
            writer.newLine();

            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
