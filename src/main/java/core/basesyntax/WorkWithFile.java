package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

    }

    public String readData(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder data = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                data.append(line);
                line = bufferedReader.readLine();
            }
            return data.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }


}
