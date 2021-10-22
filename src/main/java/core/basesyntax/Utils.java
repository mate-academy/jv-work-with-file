package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Utils {
    public static Stream<String> getFile(String name) {
        try {
            return Files.lines(Paths.get(name));
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to read " + name, e);
        }
    }

    public static void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
