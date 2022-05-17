package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadData {
    public String readData(String fileName) {
        StringBuilder data = new StringBuilder();

        try (var bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
                data.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Error read data from file '%s'", fileName), e);
        }
        return data.toString().strip();
    }
}
