package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriterService {
    public static void writeDataToFile(String[] data, String fileName) {
        File file = new File(fileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new java.io.FileWriter(file, true));
            for (String lineData: data) {
                bufferedWriter.write(lineData + System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException during writing to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException during closing BufferedWriter");
                }
            }
        }
    }
}
