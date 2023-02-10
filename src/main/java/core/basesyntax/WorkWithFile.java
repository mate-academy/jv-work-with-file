package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String[] WORDS = {"supply", "buy", "result"};
    private final int[] result = new int[3];

    public void getStatistic(String fromFileName, String toFileName) {
        // read file
        File fileRead = new File(fromFileName);
        List<String> listOfLines;
        try {
            listOfLines = Files.readAllLines(fileRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file ", e);
        }
        //count results
        for (String line : listOfLines) {
            if (line.startsWith("supply")) {
                result[0] += Integer.parseInt(line.substring(7));
            } else {
                result[1] += Integer.parseInt(line.substring(4));
            }
        }
        result[2] = result[0] - result[1];
        //check existing and clean file if it exists
        File fileWrite = new File(toFileName);
        if (fileWrite.exists()) {
            try (FileWriter clearFile = new FileWriter(fileWrite)) {
                clearFile.write("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //write file and then clear result to use method again
        for (int i = 0; i < WORDS.length; i++) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite, true))) {
                writer.write(WORDS[i] + "," + result[i] + System.lineSeparator());
                result[i] = 0;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
