package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try {
            String lines = Files.readAllLines(Paths.get(fromFileName)).toString();
            String [] line = lines.split(" ");
            line[0] = line[0].substring(1);
            for (int i = 0; i < line.length; i++) {
                line[i] = line[i].substring(0, line[i].length() - 1);
                int index1 = line[i].indexOf(',');
                if (line[i].substring(0, index1). equals("supply")) {
                    supply += Integer.parseInt(line[i].substring(index1 + 1));
                    continue;
                }
                buy += Integer.parseInt(line[i].substring(index1 + 1));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read current document", e);
        }
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy));
        } catch (IOException e) {
            System.out.println("Can`t write data in " + toFileName + ", " + e);
        }
    }
}
