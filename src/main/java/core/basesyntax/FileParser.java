package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class FileParser {

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
