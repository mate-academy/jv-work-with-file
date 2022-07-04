package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
    public String[] readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder fileLine = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                fileLine.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Where's the file?", e);
        }
        return fileLine.toString().split(System.lineSeparator());
    }
}
