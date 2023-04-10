package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SEPARATE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(",");
                int length = line.substring(index).length();
                int value = Integer.parseInt(line.substring(index + 1, index + length));
                if (line.substring(0, index).equals("supply")) {
                    supply += value;
                }
                if (line.substring(0, index).equals("buy")) {
                    buy += value;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        int result = supply - buy;
        String content = "supply," + supply + SEPARATE
                + "buy," + buy + SEPARATE
                + "result," + result;
        File file = new File(toFileName);
        BufferedWriter writer;
        try (FileWriter fWriter = new FileWriter(file)) {
            writer = new BufferedWriter(fWriter);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
