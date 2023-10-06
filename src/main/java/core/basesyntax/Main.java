package core.basesyntax;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String filePath = "test2"; 
        String[] filteredWords = FileWork.readFromFile(filePath);
        System.out.println(Arrays.toString(filteredWords));
    }
}
