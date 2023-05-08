package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int supplies = 0;
        int buyes = 0;
        int result;
        try {
            List<String> fileContent = Files.readAllLines(fromFile.toPath());
            for (String content : fileContent) {
                String action = content.split(",")[0];
                String sum = content.split(",")[1];
                if (action.equals("supply")) {
                    supplies += Integer.parseInt(sum);
                }
                if (action.equals("buy")) {
                    buyes += Integer.parseInt(sum);
                }
            }
            result = supplies - buyes;
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            String builder = "supply," + supplies + System.lineSeparator()
                    + "buy," + buyes + System.lineSeparator()
                    + "result," + result + System.lineSeparator();
            bufferedWriter.write(builder);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }
}