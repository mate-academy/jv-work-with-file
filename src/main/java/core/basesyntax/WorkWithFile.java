package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            FileReader fileReader = new FileReader(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null){
                stringBuilder.append(value).append(", ");
                value = bufferedReader.readLine();}
        } catch (IOException e) {
                throw new RuntimeException("Can't read the file");
        }

        }
    }
}
