package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDataReader {
    private static String SPLIT_BY_SPACES = " ";

    public String readFrom(String fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder data = new StringBuilder();
            for (Object line : bufferedReader.lines().toArray()) {
                data.append(line).append(SPLIT_BY_SPACES);
            }
            return data.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file: " + fromFile, e);
        }
    }

}
