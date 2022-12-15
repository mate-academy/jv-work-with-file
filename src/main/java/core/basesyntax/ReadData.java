package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {
    public String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(System.lineSeparator());
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + e);
        }
        return builder.toString();
    }
}
