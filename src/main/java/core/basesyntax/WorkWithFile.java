package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    private StringBuilder stringBuilder = new StringBuilder();
    public void getStatistic(String fromFileName, String toFileName) {

        readDataFromFile(fromFileName);

    }
    private String readDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))){
            String value = null;
            try {
                value = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't read file " + e);
            }
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                try {
                    value = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write file in StringBuilder " + e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find a file " + e);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + e);
        }
        return stringBuilder.toString();
    }
}
