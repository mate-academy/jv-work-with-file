package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ContentWriter {
    public void getNewStatisticsFile(String convertedContent, File newFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(convertedContent);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write content to the file: " + newFile, e);
        }
    }
}
