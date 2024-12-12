package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineValues = line.split(",");
                if (lineValues[0].equals("buy")) {
                    buySum += Integer.parseInt(lineValues[1]);
                } else if (lineValues[0].equals("supply")) {
                    supplySum += Integer.parseInt(lineValues[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result = supplySum - buySum;
        File toFileFile = new File(toFileName);
        try {
            Files.deleteIfExists(toFileFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot delete file " + toFileName, e);
        }
        try {
            Files.createFile(toFileFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file " + toFileName, e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write("supply," + supplySum);
            writer.newLine();
            writer.write("buy," + buySum);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
