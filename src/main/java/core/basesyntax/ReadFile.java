package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cann`t read file", e);
        }
        return stringBuilder.toString();
    }
}
