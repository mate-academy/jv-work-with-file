package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferReadWrite {

    public void writeInFile(String writeToFile, String[] report) {
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(writeToFile))) {
            for (String arr : report) {
                buffWrite.write(arr);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not write in file", e);
        }
    }

    public String readFromFile(String readFromFile) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(readFromFile))) {
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
}
