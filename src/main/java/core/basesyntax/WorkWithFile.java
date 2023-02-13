package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);

    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File has not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + file.getName(), e);
        }
        return builder.toString();
    }

    private void writeToFile() {

    }

    private String makeReport() {
return "";
    }

    public static void main(String[] args) {
        //String data = new WorkWithFile().getStatistic("apple.csv", "test");
    }
}
