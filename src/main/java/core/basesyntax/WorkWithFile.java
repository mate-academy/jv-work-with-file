package core.basesyntax;

import java.io.File;
import java.util.Arrays;

public class WorkWithFile {
    public static void main(String[] args) {
        File file = new File("apple.csv");
        ReadFile readFile = new ReadFile();
        String str = readFile.readFileContent(file);
        String[] result = str.split(System.lineSeparator());
        System.out.println(Arrays.asList(result));

    }

    public void getStatistic(String fromFileName, String toFileName) {


    }
}
