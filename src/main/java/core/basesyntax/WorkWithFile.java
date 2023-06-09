package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String fileInfo = workWithFile.readFile(fromFileName);


    }

    public String readFile(String fromFileName) {
        File fileWithInfo = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileWithInfo);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String info = bufferedReader.readLine();
            while (info != null) {
                stringBuilder.append(info).append(System.lineSeparator());
                info = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }
}