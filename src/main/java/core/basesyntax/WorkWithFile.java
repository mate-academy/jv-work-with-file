package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
       try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){


       }catch (IOException e){
        throw new RuntimeException("Can't read the file", e);
       }
    }
}
