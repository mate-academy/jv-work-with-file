package core.basesyntax;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {



    }

    public static void main(String[] args) {
        File file = new File("apple.csv");
   ReadFile readFile = new ReadFile();
   String str = readFile.readFileContent(file);
   String[] result = str.split("\\W+");
        System.out.println(Arrays.asList(result));

    }
}
