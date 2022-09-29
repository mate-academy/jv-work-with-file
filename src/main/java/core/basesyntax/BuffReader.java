package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BuffReader {

    public String readFromFile(String fromFile) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                sb.append(value).append(WorkWithFile.SPACE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file", e);
        }

        return sb.toString().trim();
    }

    public void writeInFile(String[] array, String toFile) {
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(toFile))) {
            for (String arr : array) {
                buffWrite.write(arr);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not write in file", e);
        }
    }
}
