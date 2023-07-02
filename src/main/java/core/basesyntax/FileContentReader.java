package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileContentReader {
    public static final String DEFAULT_SYSTEM_LINE_SEPARATOR = System.lineSeparator();

    public String read(String filePath) {
        if (filePath == null) {
            throw new RuntimeException("File path must not be a null");
        }

        File fileToRead = new File(filePath);
        StringBuilder fileContentBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String contentLine = bufferedReader.readLine();

            while (contentLine != null) {
                fileContentBuilder.append(DEFAULT_SYSTEM_LINE_SEPARATOR).append(contentLine);
                contentLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file to read: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file: " + filePath, e);
        }

        return fileContentBuilder.toString();
    }
}
