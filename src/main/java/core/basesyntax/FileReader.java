package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {

    public String getRawContent(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fromFileName))) {
            String value;
            StringBuilder builder = new StringBuilder();
            while ((value = reader.readLine()) != null) {
                builder.append(value).append(" ");
            }
            return String.valueOf(builder).trim();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file: " + fromFileName, e);
        }
    }
}
