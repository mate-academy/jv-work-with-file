package core.basesyntax;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        WorkWithFile workWithFile = new WorkWithFile();
        String filename = "C:\\intellijProjects\\jv-work-with-file\\src\\test.csv";
        String toFileName = "C:\\intellijProjects\\jv-work-with-file\\src\\testOut.csv";
        System.out.println(Arrays.toString(workWithFile.getStatistic(filename, toFileName)));
    }
}