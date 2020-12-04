package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private static List<String> readFile(String filePath) {
        File fileToRead = new File(filePath);
        List<String> readData = null;
        try {
            readData = Files.readAllLines(fileToRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("I wasn't able to read the file", e);
        }
        return readData;
    }

    private static void writeFile(String filePath, List<String> data) {
        File fileToWrite = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))) {
            for (int i = 0; i < data.size(); i++) {
                bw.write(data.get(i));
                if (i <= data.size() - 1) {
                    bw.write("\n");
                }
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Wow... something went wrong, couldn't write the file");
        }
    }

    private static List<String> createReport(List<String> list) {
        return null;
    }

    public void getStatistic(String fromFileName, String toFileName) {

    }
}
