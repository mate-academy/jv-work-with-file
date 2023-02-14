package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData extends WorkWithFile {
    public static void writeDataToFile(String formatedData, String direcion) {
        File managedData = new File(direcion);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(managedData))) {
            for (int i = 0; i < formatedData.length(); i++) {
                writer.write(formatedData.charAt(i));
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't write data to file " + managedData, exception);
        }
    }
}
