package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
    public void writer(String toFile,String dataToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(dataToWrite);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t write data to file",e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
