package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    protected void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;

            while (line != null) {
                String[] lineContent = line.split(",");
                if (lineContent[0].equals("supply")) {
                    supply += Integer.parseInt(lineContent[1]);
                } else if (lineContent[0].equals("buy")) {
                    buy += Integer.parseInt(lineContent[1]);
                }
                line = bufferedReader.readLine();
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                int result = supply - buy;
                bufferedWriter.write("supply," + supply);
                bufferedWriter.newLine();
                bufferedWriter.write("buy," + buy);
                bufferedWriter.newLine();
                bufferedWriter.write("result," + result);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file" + fromFileName, e);
        }
    }
}
