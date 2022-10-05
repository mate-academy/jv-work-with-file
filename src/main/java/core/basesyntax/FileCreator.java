package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public void fileCreator(String toFileName, String result) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
