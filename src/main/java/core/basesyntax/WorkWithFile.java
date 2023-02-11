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
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.contains("buy")) {
                    buy += Integer.parseInt(value.split(",")[1]);
                }
                if (value.contains("supply")) {
                    supply += Integer.parseInt(value.split(",")[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        File toFile = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + supply
                    + System.lineSeparator()
                    + "buy," + buy
                    + System.lineSeparator()
                    + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }

    }
}
