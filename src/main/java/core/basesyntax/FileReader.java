package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader {
    public String[] readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            int symbol = reader.read();
            while (symbol != -1) {
                builder.append((char) symbol);
                symbol = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        return builder.toString().split(System.lineSeparator());
    }
}
