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
                fileContentBuilder.append(contentLine).append(DEFAULT_SYSTEM_LINE_SEPARATOR);
                contentLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file to read: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file: " + filePath, e);
        }

        // To remove rightmost DEFAULT_SYSTEM_LINE_SEPARATOR
        // applied by the last iteration of reading the file content
        if (fileContentBuilder.length() > 0) {
            fileContentBuilder.setLength(
                    fileContentBuilder.length() - DEFAULT_SYSTEM_LINE_SEPARATOR.length()
            );
        }

        return fileContentBuilder.toString();
    }
}
