package core.basesyntax.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileWrite implements core.basesyntax.FileWrite {
    @Override
    public void writeFile(File file, String data) {
        try {
            Files.writeString(file.toPath(), data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
