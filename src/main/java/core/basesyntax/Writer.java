package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public void writeToFile(int[] data, String fileName) {
        File file = new File(fileName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("supply,").append(data[0])
                    .append(System.lineSeparator())
                    .append("buy,").append(data[1])
                    .append(System.lineSeparator())
                    .append("result,").append(data[0] - data[1]);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            writer.write(stringBuffer.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}
