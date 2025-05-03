package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private static final String SPACE_SEPARATOR = " ";

    public String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(SPACE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cann`t read file", e);
        }
        return stringBuilder.toString();
    }
}
