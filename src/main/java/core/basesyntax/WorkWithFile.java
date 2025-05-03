package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] resultingArray = new int[3];
        try (BufferedReader readFile = Files.newBufferedReader(Paths.get(fromFileName))) {
            String line;

            while ((line = readFile.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals("supply")) {
                    resultingArray[0] += Integer.parseInt(tokens[1]);
                } else if (tokens[0].equals("buy")) {
                    resultingArray[1] += Integer.parseInt(tokens[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        resultingArray[2] = resultingArray[0] - resultingArray[1];

        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("supply," + resultingArray[0]);
            writer.newLine();
            writer.write("buy," + resultingArray[1]);
            writer.newLine();
            writer.write("result," + resultingArray[2]);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file", e);
        }
    }
}
