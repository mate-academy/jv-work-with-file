package core.basesyntax.read.and.write;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public String[] read(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            String[] infoFromFile = new String[0];

            while ((line = reader.readLine()) != null) {
                infoFromFile = append(infoFromFile, line);
            }

            return infoFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read info from file: " + fromFileName, e);
        }
    }

    private String[] append(String[] data, String line) {
        String[] dataArray = new String[data.length + 1];
        System.arraycopy(data, 0, dataArray, 0, data.length);
        dataArray[data.length] = line;
        return dataArray;
    }
}
