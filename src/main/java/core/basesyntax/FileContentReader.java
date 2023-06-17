package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileContentReader {
    public String read(String filePath) {
        if (filePath == null) {
            throw new RuntimeException("File path must not be a null");
        }

        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file to read: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file: " + filePath, e);
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - System.lineSeparator().length());
        }

        return stringBuilder.toString();
    }
}
