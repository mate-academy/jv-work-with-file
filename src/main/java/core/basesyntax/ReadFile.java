package core.basesyntax;

import java.io.*;

public class ReadFile {
//    public static void main(String[] args) {
//        File file = new File("apple.csv");
//        ReadFile readFile = new ReadFile();
//        readFile.readFileContent(file);
//    }

    public String readFileContent(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuilder.append(readLine).append(System.lineSeparator());
                readLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}

