package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {
    public List<String> readFile(String fromFileName) {
        List<String> stringList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fromFileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringList.add(value);
                value = bufferedReader.readLine();
            }
            return stringList;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }
}
