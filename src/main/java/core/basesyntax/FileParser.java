package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileParser {
    public static final String COMMA_SEPARATOR = ",";

    public List<String> parseFileContent(String fileName) {
        List<String> fileContent = new ArrayList<>();
        try {
            fileContent = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
