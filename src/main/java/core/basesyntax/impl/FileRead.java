package core.basesyntax.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileRead implements core.basesyntax.FileRead {
    @Override
    public List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
