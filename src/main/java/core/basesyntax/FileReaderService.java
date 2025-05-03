package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReaderService {
    public static String[] readLines(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file with name " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return lines.toArray(new String[0]);
    }
}
