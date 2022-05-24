package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DataReader {
    public String[] readFile(File file) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            builder.append(strings);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String result = builder.toString().substring(1, builder.length() - 1);
        return result.split(" ");
    }
}
