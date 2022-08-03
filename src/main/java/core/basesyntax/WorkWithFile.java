package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        File before = new File(fromFileName);
        String beforePath = before.getPath();
        File result = new File(toFileName);

        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(before))) {
            List<String> strings = Files.readAllLines(Path.of(beforePath));
            for (String str : strings) {
                String line = bufferedReader.readLine();
                String[] lineElements = line.split(",");

                if ("supply".equals(lineElements[0])) {
                    supply += Integer.parseInt(lineElements[1]);
                }
                if ("buy".equals(lineElements[0])) {
                    buy += Integer.parseInt(lineElements[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(result));) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.write(System.lineSeparator() + "buy," + buy);
            bufferedWriter.write(System.lineSeparator() + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("We can not write file", e);
        }
    }
}
