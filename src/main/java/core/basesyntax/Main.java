package core.basesyntax;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File file = new File("banana.csv");
        TaskFinalizer taskFinalizer = new TaskFinalizer();
        System.out.println(taskFinalizer.finalString(new DataExtractor().extract(file)));

    }
}

