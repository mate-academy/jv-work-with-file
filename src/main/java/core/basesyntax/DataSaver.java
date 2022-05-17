package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {
    public void writeData(String fileName, String saveData) {
        try (var fileWriter = new BufferedWriter(new FileWriter(fileName))) {
            fileWriter.write(saveData);
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Error write data to file '%s'", fileName), e);
        }
    }
}
