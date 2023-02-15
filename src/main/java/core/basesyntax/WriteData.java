package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData extends WorkWithFile {
    public static void writeDataToFile(String formattedData, String direction) {
        File managedData = new File(direction);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(managedData))) {
            for (int i = 0; i < formattedData.length(); i++) {
                writer.write(formattedData.charAt(i));
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't write data to file " + managedData, exception);
        }
    }
}
