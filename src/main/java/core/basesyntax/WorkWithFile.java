package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public int[] read(String file) {
        int[] info = new int[2];
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File(file)))) {
            String line = reader.readLine();
            while (line != null) {
                String[] value = line.split(",");
                if (value[0].equals("buy")) {
                    info[0] += Integer.valueOf(value[1]);
                }
                if (value[0].equals("supply")) {
                    info[1] += Integer.valueOf(value[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read file " + file, e);
        }
        return info;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] readInfo = read(fromFileName);
        int result = readInfo[0] - readInfo[1];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write("supply," + readInfo[1] + System.lineSeparator()
                    + "buy," + readInfo[0] + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("can't write file " + toFileName, e);
        }
    }
}
