package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader lineReader = new BufferedReader(new FileReader(fromFile))) {
            String line = lineReader.readLine();
            while (line != null) {
                if ("supply".equals(line.split(",")[0])) {
                    supply += Integer.parseInt(line.split(",")[1]);
                } else {
                    buy += Integer.parseInt(line.split(",")[1]);
                }
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        result = supply - buy;
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            writer.write("supply," + supply + "\r\nbuy," + buy + "\r\nresult," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
